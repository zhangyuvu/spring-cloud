package com.zy.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zy.springcloud.entities.CommonResult;
import com.zy.springcloud.entities.Payment;
import com.zy.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author zy
 */
@RestController
@Slf4j
public class CircleBreakerController  {

    @Value("${service-url.nacos-user-service}")
    private String serviceUrl;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private PaymentService paymentService;


    @GetMapping(value = "/consumer/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id)
    {
        return paymentService.paymentSQL(id);
    }


    @RequestMapping("/consumer/fallback/{id}")
//    @SentinelResource(value = "fallback")//没有配置
//    @SentinelResource(value = "fallback" , fallback = "handlerFallback")// 只配置fallback处理异常
//    @SentinelResource(value = "fallback" , blockHandler = "handlerBlock")// 只配置blockHandler处理流控
    @SentinelResource(value = "fallback", fallback = "handlerFallback", blockHandler = "handlerBlock")// 只配置blockHandler处理流控
    public CommonResult<Payment> fallback(@PathVariable Long id)
    {
        CommonResult<Payment> result = restTemplate.getForObject(serviceUrl + "/paymentSQL/"+id,CommonResult.class);
        if (id == 4) {
            throw new IllegalArgumentException ("IllegalArgumentException,非法参数异常....");
        }else if (result.getData() == null) {
            throw new NullPointerException ("NullPointerException,该ID没有对应记录,空指针异常");
        }

        return result;
    }


    // blockHandler 对应的方法必须在最后添加一个blockException 属性
    public CommonResult<Payment> handlerBlock(@PathVariable Long id, BlockException e){
        return new CommonResult<>(444, "进入流控了  垃圾服务器  /(ㄒoㄒ)/~~  ",null);
    }

    public CommonResult handlerFallback(@PathVariable Long id, Throwable e){
        return new CommonResult(444, "兜底handler /(ㄒoㄒ)/~~  " + e.getMessage());
    }


}
