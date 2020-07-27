package com.zy.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class ConfigBeanValue {

    @Value("${aliyun.endpoint}")
    public String endpoint;

    @Value("${aliyun.accessKeyId}")
    public String accessKeyId;

    @Value("${aliyun.accessKeySecret}")
    public String accessKeySecret;

    @Value("${aliyun.bucketName}")
    public String bucketName;

    @Value("${aliyun.urlPrefix}")
    public String urlPrefix;

}

