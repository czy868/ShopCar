package com.zy.controller;

import com.aliyun.oss.model.OSSObjectSummary;
import com.zy.config.ThreadConfig;
import com.zy.entity.FileUploadResult;
import com.zy.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author czy
 */
@RestController
@RequestMapping("/file")
public class FileUploadController {
    @Autowired
    private FileUploadService fileUploadService;

    /**
     * 单线程文件上传
     * @param uploadFile
     * @return
     * @throws Exception
     */
    @PostMapping("/upload")
    public FileUploadResult upload(MultipartFile uploadFile) throws Exception {
        String key = uploadFile.getOriginalFilename();
        long len = uploadFile.getSize();
        return fileUploadService.upload(uploadFile);
    }

    /**
     * @author czy
     * @desc 绑定端口号识别客户端 文件上传到oss
     * @return FileUploadResult
     * @Param uploadFile
     */
    @PostMapping("/mutilUpload")
    public void mutilUpload(MultipartFile uploadFile) throws Exception {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 20, 300, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(3), new ThreadPoolExecutor.CallerRunsPolicy());
        ServerSocket SS = new ServerSocket(8181);
        while (true){
            executor.execute(new ThreadConfig(uploadFile));
        }
    }

    /**
     * @return FileUploadResult
     * @desc 根据文件名删除oss上的文件
     * @author czy
     * @Param objectName
     */
    @RequestMapping("/delete")
    @ResponseBody
    public FileUploadResult delete(@RequestParam("fileName") String objectName)
            throws Exception {
        return this.fileUploadService.delete(objectName);
    }

    /**
     * @author czy
     * @desc 查询oss上的所有文件
     * @return List<OSSObjectSummary>
     * @Param
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<OSSObjectSummary> list()throws Exception {
        return this.fileUploadService.list();
    }

    /**
     * @author czy
     * @desc 根据文件名下载oss上的文件
     * @return
     * @Param objectName
     */
    @RequestMapping("/download")
    @ResponseBody
    public void download(String objectName, HttpServletResponse response) throws IOException {
        //通知浏览器以附件形式下载
        response.setHeader("Content-Disposition",
                "attachment;filename=" + new String(objectName.getBytes(), "ISO-8859-1"));
        this.fileUploadService.exportOssFile(response.getOutputStream(),objectName);
    }
}

