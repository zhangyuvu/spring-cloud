package com.zy.springcloud.lb.impl;

import com.zy.springcloud.lb.LoadBalance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zy
 */
@Slf4j
@Component
public class MyLoadBalance implements LoadBalance {

    private AtomicInteger atomicInteger;  //访问次数

    public MyLoadBalance(){
        this.atomicInteger = new AtomicInteger(0);
    }

    @Override
    public ServiceInstance instance(List<ServiceInstance> instances) {

        // 服务次数 % 总微服务个数 = 调用的微服务下标 （轮询）
        int index = getAndIncrement() % instances.size();

        return instances.get(index);
    }

    private int getAndIncrement(){
        int current; // 当前访问次数
        int next;   // 本次访问过后的次数

        do{
            current = atomicInteger.get();
            next = current >= Integer.MAX_VALUE ? 1 : current + 1;
        }while (!atomicInteger.compareAndSet(current, next));
        log.info("当前访问次数：{}",next);
        return next;
    }


}
