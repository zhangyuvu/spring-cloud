package com.zy.myrule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zy
 */
@Slf4j
public class MyRoundRobinRule extends AbstractLoadBalancerRule {

    private AtomicInteger nextServerCyclicCounter;

    public MyRoundRobinRule(){
        this.nextServerCyclicCounter = new AtomicInteger(0);
    }

    public MyRoundRobinRule(ILoadBalancer lb){
        this();
        this.setLoadBalancer(lb);
    }

    @Override
    public Server choose(Object o) {
        return this.choose(getLoadBalancer(),o);
    }

    private Server choose(ILoadBalancer loadBalancer, Object o) {
        log.info("进入自定义轮询策略");
        // 没有负载均衡器则返回空
        if(loadBalancer == null){
            log.warn("no load balancer");
            return null;
        }
        Server server = null;
        int count = 0;

        while(server == null && count++  < 10) {
            List<Server> allServers = loadBalancer.getAllServers();  // 获取所有的服务
            List<Server> reachableServers = loadBalancer.getReachableServers(); // 获取所有的可到达服务（活着的服务）
            int serverCount = allServers.size();
            int upCount = reachableServers.size();
            // 活着的服务数大于0
            if(upCount > 0 && serverCount  > 0){
                // 获取下一个服务的下标
                int serverIndex = incrementAndSwap(serverCount);
                server = allServers.get(serverIndex);
                if(server == null){
                    Thread.yield();
                }else if (server.isAlive() && server.isReadyToServe()){
                    break;
                }
            }else {
                log.warn("No up servers available from load balancer: " + loadBalancer);
                break;
            }
        }
        return server;
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    // 选择下一个服务器并且更换nextServerCyclicCounter
    public int incrementAndSwap(int serverCount){
        int except;
        int next;

        do{
            except = nextServerCyclicCounter.get();
            next = (except + 1) % serverCount;
        }while (!nextServerCyclicCounter.compareAndSet(except,next));

        return next;
    }

}
