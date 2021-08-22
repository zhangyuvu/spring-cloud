package com.zy.springcloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author zy
 */
@RestController
public class OrderConsulController {

    private static final String PATH = "http://consul-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/payment/consul")
    public String  paymentzk(){
        String uri = "/payment/consul";
        String msg = restTemplate.getForObject(PATH + uri, String.class);
        return msg;
    }
}
