package com.zy.controller;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.zy.config.AliyunOSSUpload;
import com.zy.service.FileUploadMultiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.aliyun.oss.*;
/**
 * @author Berton
 * @time: 2018年2月11日 下午14:23:12
 */
@RestController
@RequestMapping("/dxcfile")
public class FileUploaderMultiController {

    @Autowired
    FileUploadMultiService fileUploadMultiService;

    /**
     * 创建固定线程池的方式
     * @param uploadFile
     * @return
     * @throws Exception
     */
    @PostMapping("/upload")
    public String fileUpload(MultipartFile uploadFile) throws Exception {
        return fileUploadMultiService.fileUpload(uploadFile);
    }

    /**
     * 使用oss的接口实现并发下载某个文件
     * @param objectName
     * @return
     */
    @RequestMapping("/dxcdownload")
    @ResponseBody
    public void fileDownload(String objectName) throws Throwable {
        fileUploadMultiService.fileDownload(objectName);
    }
}