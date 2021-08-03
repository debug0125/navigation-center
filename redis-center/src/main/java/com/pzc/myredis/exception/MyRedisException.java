package com.pzc.myredis.exception;


import com.pzc.myredis.common.MyRedisErrorEnum;

public class MyRedisException extends RuntimeException {

    private String errorCode;

    private String errorMessage;



    public MyRedisException(Throwable e) {
        super(e.getMessage());
        this.errorCode = e.getMessage();
    }


    public MyRedisException(MyRedisErrorEnum errorCodeEnum){
        super(errorCodeEnum.getErrCode());
        this.errorCode = errorCodeEnum.getErrCode();
        this.errorMessage = errorCodeEnum.getErrMsg();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }



}
