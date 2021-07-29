package com.pzc.navigationweb.application;

import com.pzc.navigationweb.common.util.Result;

/**
 * @author ryf
 * @date 7/29/21 11:10 AM
 */
public class Context<P, M> {
    private P param;
    private Result<M> result;

    public Context(P param, Result<M> result) {
        this.param = param;
        this.result = result;
    }

    public P getParam() {
        return this.param;
    }

    public void setParam(P param) {
        this.param = param;
    }

    public Result<M> getResult() {
        return this.result;
    }

    public void setResult(Result<M> result) {
        this.result = result;
    }

    public void setResultModule(M m) {
        this.getResult().setModule(m);
    }

    public ResultBuilder getResultBuilder() {
        return new ResultBuilder(this.result);
    }
}
