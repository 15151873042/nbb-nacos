package com.nbb.nacosDiscoveryConsumer.framework.ribbon;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.Server;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class SameClusterPriorityRule extends AbstractLoadBalancerRule {

    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;


    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {

    }

    @Override
    public Server choose(Object key) {
        List<NacosServer> allServers = getLoadBalancer().getAllServers().stream().map(item -> (NacosServer)item).collect(Collectors.toList());

        NacosServer nacosServer = allServers.stream()
                .filter(server -> this.nacosDiscoveryProperties.getClusterName().equals(server.getInstance().getClusterName()))
                .findFirst()
                .orElse(allServers.get((int) System.currentTimeMillis() % allServers.size()));
        return nacosServer;
    }
}
