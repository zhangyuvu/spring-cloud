package com.zy.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zy.springcloud.entities.CommonResult;
import com.zy.springcloud.entities.Payment;
import com.zy.springcloud.handler.CustomerBlockHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author zy
 */
@RestController
@Slf4j
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handleException")
    public CommonResult byResource() {
        return new CommonResult(200,"按资源名称限流测试OK",new Payment(2020L,"serial001"));
    }
    public CommonResult handleException(BlockException exception) {
        return new CommonResult(444,exception.getClass().getCanonicalName()+"   服务不可用");
    }

    @GetMapping("/rateLimit/byUrl")  // 将sentinel 的兜底方法提到另一个类中 解耦合
    @SentinelResource(value = "byUrl" ,
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handler1")
    public CommonResult byUrl() {
        return new CommonResult(200,"客户自定义规则",new Payment(2020L,"serial002"));
    }
}
