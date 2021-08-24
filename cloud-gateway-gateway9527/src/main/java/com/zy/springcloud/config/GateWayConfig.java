package com.zy.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zy
 */
@Configuration
public class GateWayConfig {

    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder){

        RouteLocatorBuilder.Builder routes = builder.routes();

        routes.route("path_route_zy",
                r -> r.path("/guonei")
                        .uri("http://news.baidu.com/guonei"))
                        .build();
        return routes.build();

    }

    @Bean
    public RouteLocator customerRouteLocator2(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routes = builder.routes();

        routes.route("path_route_niubi",
                r -> r.path("/guoji")
                        .uri("http://news.baidu.com/guoji"))
                .build();
        return routes.build();
    }
}
