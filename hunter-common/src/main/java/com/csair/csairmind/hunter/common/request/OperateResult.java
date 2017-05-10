package com.csair.csairmind.hunter.common.request;

import com.csair.csairmind.hunter.common.enums.OperateCodeHolder;
import com.csair.csairmind.hunter.common.response.ApiResponse;

import java.io.Serializable;

/**
 * Created by zhengcheng
 * 响应实体
 */
public class OperateResult implements Serializable {
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
        //给msg赋上默认值
        if(msg==null || "".equalsIgnoreCase(msg)){
            this.msg= OperateCodeHolder.getMessage(code);
        }
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String code;
    private String msg;

    public ApiResponse getResponse() {
        return response;
    }

    public void setResponse(ApiResponse response) {
        this.response = response;
    }

    private  ApiResponse response;

    public boolean isSuccess(){
        return "1".equals(code);
    }

    public void printCodeInfo(){
        System.out.println(String.format("code:%s,msg:%s",code,msg));
    }
}
