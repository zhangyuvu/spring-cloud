package com.zy.springcloud.controller;

import com.zy.springcloud.entities.CommonResult;
import com.zy.springcloud.entities.Payment;
import com.zy.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author zy
 */
@Slf4j
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private PaymentService paymentService;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){

        int result = paymentService.create(payment);
        log.info("****插入结果：{}",result);
        System.out.println("你好");
        if(result > 0){
            return new CommonResult(200, "插入数据库成功  serverPort:"+serverPort+ "，",result);
        }else {
            return new CommonResult(444, "插入数据库失败");
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){

        Payment payment = paymentService.getPaymentById(id);
        log.info("****查询的数据：{}",payment);
        System.out.println("123");
        if(payment != null){
            return new CommonResult(200, "数据查询成功 serverPort:"+serverPort+"，查询id：" + id, payment);
        }else {
            return new CommonResult(444, "没有对应数据  serverPort:"+serverPort+"，查询id：" + id,null);
        }
    }

    @GetMapping("/payment/serverPort")
    public String getServerPort(){
        return serverPort;
    }

}
