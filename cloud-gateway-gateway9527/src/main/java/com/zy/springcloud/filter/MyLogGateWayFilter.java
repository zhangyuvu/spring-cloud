package com.zy.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * @author zy
 */
@Component
@Slf4j
public class MyLogGateWayFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("*****进入全局过滤器：" + new Date());
        String username = exchange.getRequest().getQueryParams().getFirst("username");
        if(username == null){
            log.info("*****用户名为null， 非法用户");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE); // 返回不能接收的状态码
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange); // 放过 下一个过滤器进行验证
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
