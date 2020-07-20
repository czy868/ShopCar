package com.zy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author czy
 */
@SpringBootApplication
@MapperScan({"com.zy.mapper"})
public class SpringBootSsmDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSsmDemoApplication.class, args);
    }

}
