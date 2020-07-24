package com.zy.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author czy
 */
@Configuration
@PropertySource(value = {"classpath:application.properties"})
@ConfigurationProperties(prefix = "aliyun")
@Data
public class AliyunConfig {
    private String endpoint="oss-cn-beijing.aliyuncs.com";
    private String accessKeyId="LTAI4G61EhRKGBhbz52Zrb5E";
    private String accessKeySecret="4N1zBBVQOcPS3fkegpzZUyCwDk9OCD";
    private String bucketName="bucketczyno1";
    private String urlPrefix="https://bucketczyno1.oss-cn-beijing.aliyuncs.com/";

    /**
     * 连接
     * @return
     */
    @Bean
    public OSS oSSClient() {
        return new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }
}
