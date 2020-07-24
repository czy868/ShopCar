package com.zy.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author czy
 */
public interface FileUploadMultiService {
    /**
     * 一个文件分片由多个线程上传
     * @param uploadFile
     * @return
     */
    public String fileUpload(MultipartFile uploadFile);

    /**
     * 调用aliyun接口实现并发下载一个文件
     * @param objectName
     * @throws Throwable
     */
    public void fileDownload(String objectName) throws Throwable;
}
