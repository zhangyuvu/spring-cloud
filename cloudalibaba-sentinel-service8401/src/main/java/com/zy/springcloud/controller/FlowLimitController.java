package com.zy.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author zy
 */

@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() {

        // 假设处理业务耗费了0.8s
//        try {
//            TimeUnit.MILLISECONDS.sleep(800);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        log.info(Thread.currentThread().getName() + "\t" + "...testA");
        return "------testA";
    }

    /**
     * 接收testB请求
     * @return 返回testB
     */
    @GetMapping("/testB")
    public String testB() {
        log.info(Thread.currentThread().getName() + "\t" + "...testB");
        return "------testB";
    }

    // 睡眠一秒测试RT
    @GetMapping("/testD")
    public String testD() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(Thread.currentThread().getName() + "\t" + "...testD  for RT");
        return "------testB";
    }

    // 异常测试 单位时间异常数过多
    @GetMapping("/testE")
    public String testE() {

        log.info(Thread.currentThread().getName() + "\t" + "...testE  for Exception");

        int n = 10 / 0;
        System.out.println("hello world");
        return "------testB";
    }

    @GetMapping(value = "/testHotKey")
    @SentinelResource(value = "testHotKey" , blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false)String p1,
                             @RequestParam(value = "p1",required = false)String p2){
        return "testHotKey";
    }

    /*
        兜底方法
     */
    public String deal_testHotKey (String p1 , String p2 , BlockException e){
        return "--deal_testHotKey o(╥﹏╥)o";
    }

}
