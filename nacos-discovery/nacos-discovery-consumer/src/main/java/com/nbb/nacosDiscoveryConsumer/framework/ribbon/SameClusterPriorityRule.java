package com.nbb.nacosDiscoveryConsumer.framework.ribbon;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SameClusterPriorityRule extends AbstractLoadBalancerRule {

    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;

    private static Random random = new Random();


    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {

    }

    @Override
    public Server choose(Object key) {
        // 所有服务实例
        List<NacosServer> allServers = getLoadBalancer().getAllServers().stream().map(item -> (NacosServer)item).collect(Collectors.toList());

        // 集群名称相同的服务实例
        List<NacosServer> sameClusterServers = allServers.stream()
                .filter(server -> this.nacosDiscoveryProperties.getClusterName().equals(server.getInstance().getClusterName()))
                .collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(sameClusterServers)) {
            return sameClusterServers.get(random.nextInt(sameClusterServers.size()));
        }

        return allServers.get(random.nextInt(allServers.size()));
    }
}
