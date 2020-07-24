package com.zy.config;

import com.zy.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author czy
 */
public class ThreadConfig implements Runnable{

    @Autowired
    private FileUploadService fileUploadService;

    private MultipartFile uploadFile;

    /**
     * 构造方法传值文件
     * @param uploadFile
     */
    public ThreadConfig(MultipartFile uploadFile){
        this.uploadFile=uploadFile;
    }

    /**
     * 一个客户端一个线程传递文件
     */
    @Override
    public void run() {
       fileUploadService.upload(uploadFile);
    }
}
