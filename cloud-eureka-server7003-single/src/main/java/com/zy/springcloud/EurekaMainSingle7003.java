package com.zy.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author zy
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaMainSingle7003 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaMainSingle7003.class,args);
    }

}
