package com.csair.csairmind.hunter.common.request;

import com.csair.csairmind.hunter.common.ApiHolder;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by zhangcheng.
 * 请求详情解析任务
 */
public class DetalisTaskRequest extends BaseRequest implements Serializable {

    @Override
    public String getApiName() {
        return ApiHolder.DETALIS_TASK_APPLY;
    }

    @Override
    public HashMap<String, String> getSignParameters() {
        return null;
    }
}