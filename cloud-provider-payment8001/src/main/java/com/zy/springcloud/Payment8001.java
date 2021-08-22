package com.zy.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author zy
 */
@EnableDiscoveryClient
@EnableEurekaClient
@MapperScan(basePackages = "com.zy.springcloud.dao")
@SpringBootApplication
public class Payment8001 {

    public static void main(String[] args) {
        SpringApplication.run(Payment8001.class, args);
    }

}
