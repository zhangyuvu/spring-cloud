package com.zy.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author zy
 */

@MapperScan(basePackages = "com.zy.springcloud.dao")
@EnableEurekaClient
@SpringBootApplication
public class Payment8002 {

    public static void main(String[] args) {
        SpringApplication.run(Payment8002.class,args);
    }

}
