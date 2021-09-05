package com.zy.springcloud.service.impl;

import com.zy.springcloud.dao.OrderDao;
import com.zy.springcloud.domain.CommonResult;
import com.zy.springcloud.domain.Order;
import com.zy.springcloud.service.AccountService;
import com.zy.springcloud.service.OrderService;
import com.zy.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zy
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;
    @Resource
    private AccountService accountService;
    @Resource
    private StorageService storageService;

    @Override
    @GlobalTransactional(name = "fsp-create-order", rollbackFor = Exception.class)
    public void create(Order order) {
        // 1.创建订单
        log.info("------> 开始创建订单");
        orderDao.create(order);

        // 2.减少库存余额
        log.info("-------> 减少库存 begin");
        CommonResult commonResult = storageService.decrease(order.getProductId(), order.getCount());
        log.info("-------> 减少库存 end");

        // 3.减少账户余额
        log.info("-------> 减少账户余额 begin");
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("-------> 减少账户余额 end");

        // 4.修改订单状态
        log.info("-------> 修改订单状态 begin");
        orderDao.update(order.getUserId(), 0);
        log.info("-------> 修改订单状态 end");

        log.info("下订单流水结束 ， 一条龙服务 呵呵");
    }

}
