package com.zy.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author zy
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced  // 轮询的方式负载均衡
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
