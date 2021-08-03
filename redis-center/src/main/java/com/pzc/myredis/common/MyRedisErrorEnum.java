package com.pzc.myredis.common;

public enum MyRedisErrorEnum {

    KEY_IS_NULL("KEY_IS_NULL","redis存储的时候key值为null");

    private String errMsg;

    private String errCode;

    private MyRedisErrorEnum(String errCode, String errMsg){
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    private MyRedisErrorEnum(String errCode){
        this.errCode = errCode;
    }

    public static MyRedisErrorEnum getTbcpErrorCodeEnum(String code) {
        for(MyRedisErrorEnum x: MyRedisErrorEnum.values()) {
            if(x.getErrCode().equals(code)) {
                return x;
            }
        }
        return null;
    }

    public String getErrCode(){
        return this.errCode;
    }

    public String getErrMsg(){
        return this.errMsg;
    }
}
