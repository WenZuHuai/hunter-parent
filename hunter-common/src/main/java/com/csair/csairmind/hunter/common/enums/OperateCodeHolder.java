package com.csair.csairmind.hunter.common.enums;

import lombok.Getter;

/**
 * Created by zhengcheng
 */
public enum OperateCodeHolder {

    SUCCESS("1","成功"),
    FAIL("2","失败"),
    EXCEPTION("9000","处理异常"),
    SERVICE_NO_EXIST("9001","服务不存在"),
    SIGN_ERROR("9002","签名错误"),
    PARAM_ERROR("9003","参数错误"),
    INVALID_VALUE("9004","无法参数值");

    /** 操作代码 */
    @Getter
    private final String code;

    /** 描述 */
    @Getter
    private final String msg;

    OperateCodeHolder(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    /**
     * 通过枚举<code>code</code>获得msg
     * @param code
     * @return msg
     */
    public static String getMessage(String code) {
        for (OperateCodeHolder status : values()) {
            if (status.getCode().equals(code)) {
                return status.getMsg();
            }
        }
        return null;
    }


}