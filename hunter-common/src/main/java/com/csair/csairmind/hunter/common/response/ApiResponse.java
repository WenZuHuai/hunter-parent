package com.csair.csairmind.hunter.common.response;

import com.csair.csairmind.hunter.common.enums.OperateCodeHolder;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by zhengcheng
 */
@Data
public abstract class ApiResponse implements Serializable {

    private OperateCodeHolder operateCodeHolder ;

    public void printCodeInfo(){
        System.out.println(String.format("code:%s,msg:%s",operateCodeHolder.getCode(),operateCodeHolder.getMsg()));
    }
}
