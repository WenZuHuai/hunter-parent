package com.csair.csairmind.hunter.common;


import com.csair.csairmind.hunter.common.exception.ApiException;
import com.csair.csairmind.hunter.common.request.ApiRequest;
import com.csair.csairmind.hunter.common.request.OperateResult;

/**
 * Created by luohonghong on 2016/6/30.15:58
 */
public interface ApiClient {
    public OperateResult execute(ApiRequest request) throws ApiException;
}
