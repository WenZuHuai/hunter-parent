package com.csair.csairmind.hunter.common.response;

import java.io.Serializable;

/**
 * Created by zhengcheng
 */
public abstract class ApiResponse implements Serializable {
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String code="";
    private String msg="";

    public void printCodeInfo(){
        System.out.println(String.format("code:%s,msg:%s",code,msg));
    }
}
