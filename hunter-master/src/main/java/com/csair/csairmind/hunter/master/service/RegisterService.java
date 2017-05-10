package com.csair.csairmind.hunter.master.service;



import com.csair.csairmind.hunter.common.constant.SprderConstants;
import com.csair.csairmind.hunter.common.request.ApiRequest;
import com.csair.csairmind.hunter.common.request.RegisterRequest;
import com.csair.csairmind.hunter.common.response.ApiResponse;
import com.csair.csairmind.hunter.common.response.RegisterResponse;
import com.csair.csairmind.hunter.common.util.JsonUtil;
import com.csair.csairmind.hunter.common.vo.MachineInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 机器注册
 * Created by luohonghong on 2016/7/1.18:17
 */
public class RegisterService extends BasicApiService{
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public ApiResponse execute(ApiRequest request) {
        RegisterRequest req=(RegisterRequest) request;
        String machineId= UUID.randomUUID().toString().replace("-","");
        String sessionKey= UUID.randomUUID().toString().replace("-","");

        MachineInfo machineInfo=new MachineInfo();
        machineInfo.setIp(req.getIp());
        machineInfo.setMachineId(machineId);
        machineInfo.setMac(req.getMac());
        machineInfo.setSessionKey(sessionKey);
        String regeidtTime = df.format(new Date());
        machineInfo.setRegTime(regeidtTime);
        machineInfo.setUpdateTime(regeidtTime);

        //从尾部插入
        stringRedisTemplate.opsForHash().put(SprderConstants.MACHINE_QUEUE_PREFIX, machineId, JsonUtil.fromObject(machineInfo));
        System.out.println("有新机器注册进来，机器为：" + machineInfo.getIp() + "|" + machineInfo.getMac() + "|"+machineId);
        RegisterResponse response=new RegisterResponse();
        response.setMachineId(machineId);
        response.setSessionKey(sessionKey);
        response.setSecrType(0);
        response.setCode("1");
        response.setMsg("注册成功");
        return response;
    }
}
