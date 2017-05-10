package com.csair.csairmind.hunter.master.config;

import com.alibaba.dubbo.config.spring.ServiceBean;
import com.csair.csairmind.hunter.common.inf.MgrService;
import com.csair.csairmind.hunter.common.plug.DubboBaseConfig;
import com.csair.csairmind.hunter.master.inf.MgrServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MgrServiceConfig extends DubboBaseConfig {
    
    @Bean
    public ServiceBean<MgrService> personServiceExport(MgrService mgrService) {
        ServiceBean<MgrService> serviceBean = new ServiceBean<MgrService>();
        serviceBean.setProxy("javassist");
        serviceBean.setVersion("Mgr-version");
        serviceBean.setInterface(MgrServiceImpl.class.getName());
        serviceBean.setRef(mgrService);
        serviceBean.setTimeout(5000);
        serviceBean.setRetries(3);
        return serviceBean;
    }

}