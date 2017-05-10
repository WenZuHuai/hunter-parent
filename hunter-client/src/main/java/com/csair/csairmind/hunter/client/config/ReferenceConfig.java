package com.csair.csairmind.hunter.client.config;

import com.alibaba.dubbo.config.spring.ReferenceBean;
import com.csair.csairmind.hunter.common.inf.MgrService;
import com.csair.csairmind.hunter.common.plug.DubboBaseConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReferenceConfig extends DubboBaseConfig {

    @Bean
    public ReferenceBean<MgrService> person() {
        ReferenceBean<MgrService> ref = new ReferenceBean<MgrService>();
        ref.setVersion("Mgr-version");
        ref.setInterface(MgrService.class);
        ref.setTimeout(5000);
        ref.setRetries(3);
        ref.setCheck(false);
        return ref;
    }
}