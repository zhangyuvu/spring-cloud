package com.zy.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {

    @Resource
    private OpenFeignService openFeignService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable(value = "id")Integer id){
        return openFeignService.paymentInfo_OK(id);
    }

//    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod",commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="4000")
//    }) // 设置最大等待时间为4s 超时则服务降级

    // 使用全局fallBack时 只要不单独指定fallbackMethod属性 那么出了问题就会去找全局fallback
    @HystrixCommand(commandProperties =  // 采用全局配置
            {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="2000")})
    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_Timeout(@PathVariable(value = "id")Integer id){
        return openFeignService.paymentInfo_Timeout(id);
    }

    @GetMapping("/payment/hystrix/timeout3s/{id}")
    @HystrixCommand(commandProperties =
            {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "2000")})
    public String paymentInfo_Timeout3(@PathVariable(value = "id")Integer id){
        return openFeignService.paymentInfo_Timeout3(id);
    }

    public String paymentTimeOutFallbackMethod(@PathVariable(value = "id")Integer id){
        return "服务80访问8001服务超时或者出错，请稍后再试。";
    }

    // 全局fallBack  不能有形参
    public String payment_Global_FallbackMethod(){
        return "当前服务繁忙，请稍后再试 /(ㄒoㄒ)/~~";
    }

}



