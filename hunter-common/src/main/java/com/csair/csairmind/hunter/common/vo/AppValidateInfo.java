package com.csair.csairmind.hunter.common.vo;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by zhangcheng on 2017/5/11 0011.
 */
@Data
@ToString
@Component
@ConfigurationProperties(prefix = "hunter.app")
public class AppValidateInfo {
    private String appKey;
    private String appSecret;
}
