package com.zy.springcloud.service;

import com.zy.springcloud.entities.CommonResult;
import com.zy.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

/**
 * @author zy
 */
@Component
public class PaymentFallbackService implements PaymentService {
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(44444,"服务降级返回,---PaymentFallbackService",new Payment(id,"errorSerial"));
    }
}
