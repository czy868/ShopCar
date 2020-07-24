package com.zy.serviceimpl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.DownloadFileRequest;
import com.aliyun.oss.model.DownloadFileResult;
import com.zy.config.AliyunOSSUpload;
import com.zy.controller.FileUploaderMultiController;
import com.zy.service.FileUploadMultiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author czy
 */
@Service
public class FileUploadMultiServiceImpl implements FileUploadMultiService {

    private static String endpoint = "oss-cn-beijing.aliyuncs.com";;
    private static String accessKeyId = "LTAI4G61EhRKGBhbz52Zrb5E";
    private static String accessKeySecret = "4N1zBBVQOcPS3fkegpzZUyCwDk9OCD";
    private static String bucketName = "bucketczyno1";
    public static OSSClient client = null;
    private static Logger logger = LoggerFactory.getLogger(FileUploaderMultiController.class);

    /**
     * 一个文件分片由多个线程上传
     * @param uploadFile
     * @return
     */
    @Override
    public String fileUpload(MultipartFile uploadFile) {
        System.out.println(uploadFile.getSize());
        Date date = new Date();
        String beginTime = date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        String key = uploadFile.getOriginalFilename();
        client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {
            String uploadId = AliyunOSSUpload.claimUploadId(bucketName, key);
            final long partSize = 5 * 1024 * 1024L;
            long fileLength = uploadFile.getSize();
            int partCount = (int) (fileLength / partSize);
            if (fileLength % partSize != 0) {
                partCount++;
            }
            if (partCount > 10000) {
                throw new RuntimeException("文件过大(分块大小不能超过10000)");
            } else {
                logger.info("一共分了 " + partCount + " 块");
            }
            for (int i = 0; i < partCount; i++) {
                long startPos = i * partSize;
                long curPartSize = (i + 1 == partCount) ? (fileLength - startPos) : partSize;
                executorService.execute(new AliyunOSSUpload(uploadFile, startPos, curPartSize, i + 1, uploadId, key, bucketName));
            }
            executorService.shutdown();
            while (!executorService.isTerminated()) {
                try {
                    executorService.awaitTermination(5, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            /**
             * partETags(上传块的ETag与块编号（PartNumber）的组合) 如果校验与之前计算的分块大小不同，则抛出异常
             */
            System.out.println(AliyunOSSUpload.partETags.size() + " -----   " + partCount);
            if (AliyunOSSUpload.partETags.size() != partCount) {
                throw new IllegalStateException("OSS分块大小与文件所计算的分块大小不一致");
            } else {
                logger.info("将要上传的文件名  " + key + "\n");
            }

            /*
             * 列出文件所有的分块清单并打印到日志中，该方法仅仅作为输出使用
             */
            AliyunOSSUpload.listAllParts(uploadId);

            /*
             * 完成分块上传
             */
            AliyunOSSUpload.completeMultipartUpload(uploadId);

            // 返回上传文件的URL地址
            // return endpoint.replaceFirst("http://","http://"+bucketName+".")+"/"+key ;
            Date date2 = new Date();
            String endTime = date2.getHours() + ":" + date2.getMinutes() + ":" + date2.getSeconds();
            return beginTime + "========" + endTime;

        } catch (Exception e) {
            logger.error("上传失败！", e);
            return "上传失败！";
        } finally {
            AliyunOSSUpload.partETags.clear();
            if (client != null) {
                client.shutdown();
            }
        }
    }

    /**
     * 调用aliyun接口实现并发下载一个文件
     * @param objectName
     * @throws Throwable
     */
    @Override
    public void fileDownload(String objectName) throws Throwable {
        OSS ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 下载请求，10个任务并发下载，启动断点续传。
        DownloadFileRequest downloadFileRequest = new DownloadFileRequest(bucketName, objectName);
        downloadFileRequest.setDownloadFile("test.png");
        downloadFileRequest.setPartSize(1 * 1024 * 1024);
        downloadFileRequest.setTaskNum(10);
        downloadFileRequest.setEnableCheckpoint(true);
        downloadFileRequest.setCheckpointFile("test1.png");
        // 下载文件。
        DownloadFileResult downloadRes = ossClient.downloadFile(downloadFileRequest);// 下载成功时，会返回文件元信息。
        downloadRes.getObjectMetadata();
        // 关闭OSSClient。
        ossClient.shutdown();
    }
}
