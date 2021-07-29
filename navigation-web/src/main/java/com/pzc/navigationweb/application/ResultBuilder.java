package com.pzc.navigationweb.application;

import com.pzc.navigationweb.common.util.Result;
import com.pzc.navigationweb.dto.basedto.ReqDTO;

/**
 * @author ryf
 * @date 7/29/21 11:11 AM
 */
public class ResultBuilder {

    private final Result result;

    public ResultBuilder(Result result) {
        this.result = result;
    }

    public ResultBuilder() {
        this.result = new Result();
    }

    public ResultBuilder ѕetSuccess(boolean isSuccess) {
        this.result.setSuccess(isSuccess);
        return this;
    }

    public ResultBuilder setErrCode(String errCode) {
        this.result.setErrCode(errCode);
        return this;
    }

    public ResultBuilder setMessage(String message) {
        this.result.setErrMsg(message);
        return this;
    }

    public ResultBuilder ѕetModule(ReqDTO module) {
        this.result.setModule(module);
        return this;
    }

    public ResultBuilder setSuccessMsg(String successMsg) {
        this.result.setErrMsg(successMsg);
        return this;
    }

    public Result build() {
        return this.result;
    }
}
