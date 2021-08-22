package com.zy.springcloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author zy
 */
@RestController
public class OrderZkController {

    private static final String PATH = "http://cloud-payment-service";

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/payment/zk")
    public String  paymentzk(){
        String uri = "/payment/zk";
        String msg = restTemplate.getForObject(PATH + uri, String.class);
        return msg;
    }
}
