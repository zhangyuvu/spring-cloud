package com.zy.springcloud.controller;

import com.zy.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zy
 */

@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private PaymentService paymentService;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable(value = "id")Integer id){
        String result =  paymentService.paymentInfo_OK(id);
        log.info(result);
        return result;
    }
    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_Timeout(@PathVariable(value = "id")Integer id){
        String result =  paymentService.paymentInfo_Timeout(id);
        log.info(result);
        return result;
    }

    @GetMapping("/payment/hystrix/timeout3s/{id}")
    public String paymentInfo_Timeout3(@PathVariable(value = "id")Integer id){
        String result =  paymentService.paymentInfo_Timeout3(id);
        log.info(result);
        return result;
    }
}
