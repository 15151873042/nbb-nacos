package com.nbb.nacos.controller;

import com.nbb.nacos.properties.SystemConfigProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {


    @Autowired
    private SystemConfigProperties systemConfigProperties;




    @RequestMapping("")
    public Object get() {
        Map<String, Object> result = new HashMap<>();
        result.put("userCache", systemConfigProperties.isUserCache());
        return result;
    }
}
