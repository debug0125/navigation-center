package com.pzc.navigationweb.exception;

/**
 * @author ryf
 * @date 7/29/21 11:19 AM
 */
public class IdempotentException extends RuntimeException  {
    private String businessKey;
    private String businessValue;

    public IdempotentException(String businessKey) {
        this.businessKey = businessKey;
    }

    public IdempotentException(String businessKey, String businessValue) {
        this.businessKey = businessKey;
        this.businessValue = businessValue;
    }

    public String getBusinessKey() {
        return this.businessKey;
    }

    public String getBusinessValue() {
        return this.businessValue;
    }
}
