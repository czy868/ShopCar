package com.zy.service;

import com.aliyun.oss.model.OSSObjectSummary;
import com.zy.entity.FileUploadResult;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @author czy
 */
public interface FileUploadService {
    public FileUploadResult upload(MultipartFile uploadFile);
    public String getFilePath(String sourceFileName);
    public List<OSSObjectSummary> list();
    public FileUploadResult delete(String objectName);
    public void exportOssFile(OutputStream os, String objectName) throws IOException;

}
