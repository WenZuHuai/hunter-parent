package com.csair.csairmind.hunter.master.inf;

import com.alibaba.dubbo.rpc.RpcContext;
import com.csair.csairmind.hunter.common.ApiHolder;
import com.csair.csairmind.hunter.common.config.AppValidateInfo;
import com.csair.csairmind.hunter.common.constant.SprderConstants;
import com.csair.csairmind.hunter.common.context.ApiContext;
import com.csair.csairmind.hunter.common.context.ApiContextImpl;
import com.csair.csairmind.hunter.common.enums.OperateCodeHolder;
import com.csair.csairmind.hunter.common.inf.MgrService;
import com.csair.csairmind.hunter.common.request.ApiRequest;
import com.csair.csairmind.hunter.common.request.OperateResult;
import com.csair.csairmind.hunter.common.response.ApiResponse;
import com.csair.csairmind.hunter.common.util.ApiUtils;
import com.csair.csairmind.hunter.common.util.JsonUtil;
import com.csair.csairmind.hunter.common.vo.MachineInfo;
import com.csair.csairmind.hunter.master.factory.ServiceFactory;
import com.csair.csairmind.hunter.master.service.IApiService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by zhangcheng
 * 工作节点注册
 */
@Slf4j
public class MgrServiceImpl implements MgrService {

    @Autowired
    AppValidateInfo appValidateInfo;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public OperateResult execute(String appKey, String machineId, String sign, ApiRequest request) {
        OperateResult result=new OperateResult();
        String clientIP = RpcContext.getContext().getRemoteHost();

        try{
            log.info(String.format("收到来自[%s]命令:%s\t参数[appKey=%s,machineId=%s,timestamp=%s]\t",clientIP,request.getApiName(),appKey,machineId,request.getTimestamp()));

            //检测参数
            if(StringUtils.isBlank(request.getTimestamp())){
                result.setCode(OperateCodeHolder.INVALID_VALUE.getCode());
                result.setMsg("timestamp不能为空");
                log.info(String.format("请求结果:[%s,%s]\r\n",result.getCode(),result.getMsg()));
                return result;
            }
            //超过15分钟的时间戳视为无效
            Date requestTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(request.getTimestamp());
            //15分钟前
            Date endDateTime=new Date(new Date().getTime()- 60 * 1000*15);
            if(DateUtils.truncatedCompareTo(requestTime,endDateTime , Calendar.SECOND)<0){
                result.setCode(OperateCodeHolder.INVALID_VALUE.getCode());
                result.setMsg("timestamp超过15分钟!");
                log.info(String.format("请求结果:[%s,%s]\r\n",result.getCode(),result.getMsg()));
                return result;
            }
            //appKey
            if(appKey==null ||appKey.trim().equalsIgnoreCase("")){
                result.setCode(OperateCodeHolder.INVALID_VALUE.getCode());
                result.setMsg("appKey不能为空!");
                log.info(String.format("请求结果:[%s,%s]\r\n",result.getCode(),result.getMsg()));
                return result;
            }

            //创建服务对象
            IApiService iApiService= ServiceFactory.getService(request.getApiName());
            if(iApiService==null){
                result.setCode(OperateCodeHolder.SERVICE_NO_EXIST.getCode());
                log.info(String.format("请求结果:[%s,%s]\r\n",result.getCode(),result.getMsg()));
                return result;
            }

            //签名验证
            HashMap<String,String> parameters= request.getSignParameters();
            if(parameters==null){
                parameters=new HashMap<String, String>();
            }
            parameters.put("machineId",machineId);
            parameters.put("appKey",appKey);
            parameters.put("appSecret",appValidateInfo.getAppSecret());
            parameters.put("timestamp",request.getTimestamp());
            String sign2= ApiUtils.signRequest(appKey, appValidateInfo.getAppSecret(), parameters);
            if(sign==null ||"".equalsIgnoreCase(sign2)||!sign2.equalsIgnoreCase(sign)){
                result.setCode(OperateCodeHolder.SIGN_ERROR.getCode());
                result.setMsg(OperateCodeHolder.SIGN_ERROR.getMsg());
                log.info(String.format("请求结果:[%s,%s]\r\n",result.getCode(),result.getMsg()));
                return result;
            }

            //设置ApiContext，以便具体实现类参数传递
            ApiContext context=new ApiContextImpl();

            if(ApiHolder.REGISTER.equalsIgnoreCase(request.getApiName())){
                //注册服务令，不需要获取机器信息
            }else{
                //检测协议字段
                if(machineId==null ||machineId.trim().equalsIgnoreCase("")){
                    result.setCode(OperateCodeHolder.INVALID_VALUE.getCode());
                    result.setMsg("machineId不能为空");
                    log.info(String.format("请求结果:[%s,%s]\r\n",result.getCode(),result.getMsg()));
                    return result;
                }
                context.setMachineId(machineId);
                Object machine = stringRedisTemplate.opsForHash().get(SprderConstants.MACHINE_QUEUE_PREFIX,machineId);
                if(machine==null||machine.equals(""))
                {
                    result.setCode(OperateCodeHolder.INVALID_VALUE.getCode());
                    result.setMsg("注册信息不存在");
                    log.info(String.format("请求结果:[%s,%s]\r\n",result.getCode(),result.getMsg()));
                    return result;
                }else{
                    MachineInfo machineInfo = (MachineInfo) JsonUtil.toBean(machine.toString(),MachineInfo.class);
                    context.setMachineInfo(machineInfo);
                }
            }
            iApiService.setApiContext(context);
            result.setCode(OperateCodeHolder.SUCCESS.getCode());
            ApiResponse response=  iApiService.execute(request);
            result.setResponse(response);
            log.info(String.format("请求结果:[%s,%s]\r\n",result.getCode(),result.getMsg()));
            return result;
        }catch (Exception ex){
            result.setCode(OperateCodeHolder.EXCEPTION.getCode());
            log.error("机器注册异常",ex);
        }
        return result;
    }
}
