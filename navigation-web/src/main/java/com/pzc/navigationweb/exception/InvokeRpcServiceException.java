package com.pzc.navigationweb.exception;

/**
 * @author ryf
 * @date 7/29/21 11:18 AM
 */
public class InvokeRpcServiceException extends RuntimeException  {
    private String rpcServiceName;

    public InvokeRpcServiceException(String rpcServiceName, Throwable e) {
        super(e);
        this.rpcServiceName = rpcServiceName;
    }

    public InvokeRpcServiceException(String message, String rpcServiceName, Throwable e) {
        super(message, e);
        this.rpcServiceName = rpcServiceName;
    }

    public String getRpcServiceName() {
        return this.rpcServiceName;
    }
}
