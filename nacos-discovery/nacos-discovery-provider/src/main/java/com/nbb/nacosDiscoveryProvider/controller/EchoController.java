package com.nbb.nacosDiscoveryProvider.controller;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoController {

    @Value("${server.port}")
    private String port;

    @Autowired
    private NacosDiscoveryProperties NacosDiscoveryProperties;

    @RequestMapping("/echo/{string}")
    public String echo(@PathVariable String string) {
        return "Hello Nacos Discovery " + string + "(端口：" + port + "，集群名称：" + NacosDiscoveryProperties.getClusterName() +")";
    }
}
