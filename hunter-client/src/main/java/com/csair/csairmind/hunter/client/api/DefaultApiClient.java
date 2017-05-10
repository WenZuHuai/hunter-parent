package com.csair.csairmind.hunter.client.api;



import com.csair.csairmind.hunter.client.content.DefaultApplicationContext;
import com.csair.csairmind.hunter.common.ApiClient;
import com.csair.csairmind.hunter.common.request.ApiRequest;
import com.csair.csairmind.hunter.common.request.OperateResult;
import com.csair.csairmind.hunter.common.util.ApiUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * 用于接口统一请求服务
 * Created by zhangcheng
        */

public class DefaultApiClient implements ApiClient {
    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public String machineId;
    public String appKey;
    public String appSecret;

    @Override
    public OperateResult execute(ApiRequest request) {
        OperateResult result=new OperateResult();
        try{
            //加上其他需要签名的字段
            request.setTimestamp(df.format(new Date()));
            HashMap<String,String> parameters=request.getSignParameters();
            if(parameters==null)
                parameters=new HashMap<String, String>();
            parameters.put("machineId",machineId);
            parameters.put("appKey",appKey);
            parameters.put("appSecret",appSecret);
            parameters.put("timestamp",request.getTimestamp());
            String signStr= ApiUtils.signRequest(appKey, appSecret, parameters);
            //通过dubble协议来请求服务
            result= DefaultApplicationContext.context().getMgrService().execute(appKey,machineId, signStr, request);
        }catch (Exception e){
            System.out.println("请求出错："+request.getApiName());
        }
        return result;
    }
}
