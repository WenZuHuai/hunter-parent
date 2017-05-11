package com.csair.csairmind.hunter.master.service;



import com.csair.csairmind.hunter.common.constant.SprderConstants;
import com.csair.csairmind.hunter.common.request.ApiRequest;
import com.csair.csairmind.hunter.common.response.ApiResponse;
import com.csair.csairmind.hunter.common.response.BeatResponse;
import com.csair.csairmind.hunter.common.util.JsonUtil;
import com.csair.csairmind.hunter.common.vo.MachineInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 心跳服务
 * Created by luohonghong on 2016/7/1.16:33
 */
public class BeatService extends BasicApiService{
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    @Override
    public ApiResponse execute(ApiRequest request) {
//        String machineId=getApiContext().getMachineId();
        BeatResponse response=new BeatResponse();
//        stringRedisTemplate.execute(new RedisCallback<Long>() {
//            @Override
//            public Long doInRedis(RedisConnection connection) throws DataAccessException {
//                RedisSerializer<String> serializer = stringRedisTemplate.getStringSerializer();
//                long count = connection.hExists()
//                return count;
//            }
//        })
//        if(ShardRedis.userShard().hexists(SprderConstants.MACHINE_QUEUE_PREFIX,machineId)){
//            String json = ShardRedis.userShard().hget(SprderConstants.MACHINE_QUEUE_PREFIX, machineId);
//            MachineInfo machineInfo=(MachineInfo) JsonUtil.toBean(json,MachineInfo.class);
//            String updateTime = df.format(new Date());
//            machineInfo.setUpdateTime(updateTime);
//            ShardRedis.userShard().hset(SprderConstants.MACHINE_QUEUE_PREFIX, machineId, JsonUtil.fromObject(machineInfo));
//            response.setOk(true);
//        }else{
//            response.setOk(false);
//            System.out.println("收到心跳处理失败!...");
//        }
        return response;
    }

}

