package com.csair.csairmind.hunter.common.plug;

import com.alibaba.dubbo.config.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DubboBaseConfig {

    @Bean
    @ConfigurationProperties(prefix="spring.dubbo")
    public RegistryConfig registry() {
        RegistryConfig registryConfig = new RegistryConfig();
        return registryConfig;
    }
    
    @Bean
    @ConfigurationProperties(prefix="spring.dubbo")
    public ApplicationConfig application() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        return applicationConfig;
    }
    
    @Bean
    public MonitorConfig monitorConfig() {
        MonitorConfig mc = new MonitorConfig();
        mc.setProtocol("registry");
        return mc;
    }
    
    @Bean
    public ReferenceConfig referenceConfig() {
        ReferenceConfig rc = new ReferenceConfig();
        rc.setMonitor(monitorConfig());
        return rc;
    }
    
    @Bean
    public ProtocolConfig protocol() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setPort(20880);
        return protocolConfig;
    }
    
    @Bean
    public ProviderConfig provider() {
        ProviderConfig providerConfig = new ProviderConfig();
        providerConfig.setMonitor(monitorConfig());
        return providerConfig;
    }
    
    
}