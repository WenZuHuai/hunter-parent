package com.csair.csairmind.hunter.master.service;


import com.csair.csairmind.hunter.common.context.ApiContext;
import com.csair.csairmind.hunter.common.request.ApiRequest;
import com.csair.csairmind.hunter.common.response.ApiResponse;

/**
 * 最顶层的接口类
 * Created by zhangcheng
 */
public interface IApiService {
    /**
     * 用于处理请求的接口
     * @return
     */
    public ApiResponse execute(ApiRequest request) ;

    public ApiContext getApiContext();
    public void setApiContext(ApiContext apiContext);


}
