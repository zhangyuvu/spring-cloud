package com.zy.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author zy
 */

@Service
public class PaymentService {

    public String paymentInfo_OK(Integer id){

        return "线程池: " + Thread.currentThread().getName() + " === paymentInfo_OK , id = " + id ;
    }

    /*
        自测时间是否超时，超时则服务降级 调用fallBack的方法
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler"/*指定善后方法名*/,commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="3000")
    })
    public String paymentInfo_Timeout(Integer id) {

        int time = 5; // 需要处理4s
//        int i = 10 / 0;
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "线程池: " + Thread.currentThread().getName() + " === paymentInfo_timeout , id = " + id +" 耗时:" + time + "s";
    }

    // 善后的方法
    public String paymentInfo_TimeOutHandler(Integer id){
        return "线程池:  "+Thread.currentThread().getName()+"  8001系统繁忙或者运行报错，请稍后再试,id:  "+id+"\t"+"o(╥﹏╥)o";
    }
}
