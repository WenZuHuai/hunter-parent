package com.csair.csairmind.hunter.client.service;


import com.csair.csairmind.hunter.client.api.DefaultApiClient;
import com.csair.csairmind.hunter.client.content.DefaultApplicationContext;
import com.csair.csairmind.hunter.common.request.OperateResult;
import com.csair.csairmind.hunter.common.request.RegisterRequest;
import com.csair.csairmind.hunter.common.response.RegisterResponse;
import com.csair.csairmind.hunter.common.util.ApiUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.text.SimpleDateFormat;

/**
 * 方便重复调用，只是简单的包了一下服务接口
 * Created by luohonghong on 2016/7/1.17:13
 */
@Slf4j
public class WrapService {

    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Value("${appKey}")
    String appKey;
    @Value("${appSecret}")
    String appSecret;
    /**
     * 机器注册
     * @return
     */
    public boolean register() {
        try{
            String machineId= DefaultApplicationContext.context().getMachineId();
            DefaultApiClient client=new DefaultApiClient();
            client.appKey=appKey;
            client.appSecret=appSecret;
            client.machineId=machineId;

            RegisterRequest request=new RegisterRequest();
            request.setIp(ApiUtils.getLocalIp());
            request.setMac(ApiUtils.getLocalMac());
//            Double pid = Math.random()*9000+1000;
//            request.setPid(pid.intValue());
            OperateResult result=client.execute(request);
            if(result.isSuccess()){
                RegisterResponse response=(RegisterResponse)result.getResponse();
                if("1".equalsIgnoreCase(response.getCode())){
                    DefaultApplicationContext.context().setMachineId(response.getMachineId());
                    DefaultApplicationContext.context().setSessionKey(response.getSessionKey());
                    log.info("注册成功!");
                    return true;
                }else{
                    log.info(String.format("机器注册结果：[%s,%s]", response.getCode(), response.getMsg()));
                }
            }else{
                log.info(String.format("机器注册失败：[%s,%s]", result.getCode(), result.getMsg()));
            }
        }catch (Exception ex){
            log.error("机器注册异常",ex);
        }
        return false;
    }
}
