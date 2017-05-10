package com.csair.csairmind.hunter.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by luohonghong on 2016/7/2.13:39
 */
@Data
public class MachineInfo implements Serializable {
    /**
     * 机器ID
     */
    private String machineId;

    /**
     * 会话密钥，可用于加解密
     */
    private String sessionKey;

    private String mac;
    private String ip;
    private String regTime;
    private String updateTime;
}
