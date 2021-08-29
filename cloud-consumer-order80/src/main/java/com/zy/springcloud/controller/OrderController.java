package com.zy.springcloud.controller;

import com.zy.springcloud.entities.CommonResult;
import com.zy.springcloud.entities.Payment;
import com.zy.springcloud.lb.LoadBalance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zy
 */
@RestController
@Slf4j
public class OrderController {

//    public static final String PAYMENT_URL = "http://localhost:8001"; // 调用的服务的地址不能再写死了
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private LoadBalance loadBalance;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin()
    {
        String result = restTemplate.getForObject("http://localhost:8001"+"/payment/zipkin/", String.class);
        return result;
    }

    @PostMapping("/consumer/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        String uri = "/payment/create";
//        return restTemplate.postForObject(PAYMENT_URL + uri, payment, CommonResult.class);
        return restTemplate.postForEntity(PAYMENT_URL + uri,payment,CommonResult.class).getBody();
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        String uri = "/payment/get/" + id;
        return restTemplate.getForObject(PAYMENT_URL + uri, CommonResult.class);
    }

    @GetMapping("/consumer/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();

        for (String service : services) {
            log.info("暴露的服务：{}",service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");

        for (ServiceInstance instance : instances) {
            log.info("服务id：{}  \t 服务ip：{}  \t 服务端口：{}  \t 服务uri：{}"
                    ,instance.getServiceId(),instance.getHost(),instance.getPort(),instance.getUri());
        }
        
        return discoveryClient;
    }

    @GetMapping("/consumer/payment/serverPort")
    public String getServerPort(){

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");

        if(instances == null || instances.size() == 0){
            return null;
        }

        ServiceInstance instance =
                loadBalance.instance(instances);

        String uri = instance.getUri().toString();
        log.info( "服务uri为：{}" , uri);
        return restTemplate.getForObject( uri + "/payment/serverPort",String.class);
    }

}
