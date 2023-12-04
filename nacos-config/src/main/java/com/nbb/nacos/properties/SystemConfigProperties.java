package com.nbb.nacos.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@RefreshScope
@ConfigurationProperties("system.config")
@Component
public class SystemConfigProperties implements Serializable {

    private boolean userCache;
}
