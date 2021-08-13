package com.pzc.navigationweb.constant.enumtype;

import lombok.Data;

/**
 * @author ryf
 * @date 8/13/21 1:28 PM
 */
public enum  LotteryType {
    SYSTEM_NUM(1,"中奖号码"),
    CUSTOM_NUM(2,"自定义号码"),
    ;

    private int type;
    private String remark;

    private LotteryType(int type, String remark) {
        this.type =  type;
        this.remark = remark;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }}
