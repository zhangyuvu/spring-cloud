package com.zy.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zy
 */
@Configuration
public class MyRuleConfig {

    @Bean
    public IRule myRule(){
        return new MyRoundRobinRule();
    }

}
