package com.zy.springcloud.dao;

import com.zy.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

//@Mapper
public interface PaymentDao {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);

}
