package com.zy.springcloud.controller;

import com.zy.springcloud.service.OpenFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zy
 */
@RestController
@Slf4j
public class OrderHystrixController {

    @Resource
    private OpenFeignService openFeignService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable(value = "id")Integer id){
        return openFeignService.paymentInfo_OK(id);
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_Timeout(@PathVariable(value = "id")Integer id){
        return openFeignService.paymentInfo_Timeout(id);
    }

}



