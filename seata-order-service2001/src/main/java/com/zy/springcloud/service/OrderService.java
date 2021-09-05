package com.zy.springcloud.service;


import com.zy.springcloud.dao.OrderDao;
import com.zy.springcloud.domain.Order;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

public interface OrderService {

    void create(Order order);

}

