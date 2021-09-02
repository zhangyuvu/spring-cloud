package com.zy.springcloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zy.springcloud.entities.CommonResult;

/**
 * @author zy
 */
public class CustomerBlockHandler {

    public static CommonResult handler1(BlockException b){
        return new CommonResult(444,"global handler -- 1");
    }

    public static CommonResult handler2(BlockException b){
        return new CommonResult(444,"global handler -- 2");
    }
}


