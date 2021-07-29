package com.pzc.navigationweb.constant.enumtype;

/**
 * @author ryf
 * @date 7/29/21 11:21 AM
 */
public enum CommonExceptionEnum {
    IDEMPOTENT_INVOKE_ERROR("IDEMPOTENT_INVOKE_ERROR", "幂等异常: 业务键{}已操作成功，请勿重复操作！"),
    INVOKE_RPC_SERVICE_ERROR("INVOKE_RPC_SERVICE_ERROR", "调用远程RPC服务异常:调用服务名{}，错误详情{}"),
    INTERNAL_ERROR("INTERNAL_ERROR", "内部服务错误");

    private String errMsg;
    private String errCode;

    private CommonExceptionEnum(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getErrCode() {
        return this.errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }
}
