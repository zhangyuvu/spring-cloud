package com.zy.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements OpenFeignService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "PaymentFallbackService paymentInfo_OK fall bcak";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "PaymentFallbackService paymentInfo_Timeout fall bcak";
    }

    @Override
    public String paymentInfo_Timeout3(Integer id) {
        return "PaymentFallbackService paymentInfo_Timeout3 fall bcak";
    }
}
