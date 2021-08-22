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

//    @LoadBalanced // 这个模板 发送的http请求以轮询的方式实现负载均衡
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
