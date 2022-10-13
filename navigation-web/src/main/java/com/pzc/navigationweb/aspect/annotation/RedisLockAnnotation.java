package com.pzc.navigationweb.aspect.annotation;

import com.pzc.navigationweb.constant.enumtype.RedisLockTypeEnum;

import java.lang.annotation.*;

/**
 * @author ryf
 * @date 5/17/21 2:10 PM
 */
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisLockAnnotation {
    /**
     * 特定参数识别，默认取第 0 个下标
     */
    int lockFiled() default 0;
    /**
     * 超时重试次数
     */
    int tryCount() default 3;
    /**
     * 自定义加锁类型
     */
    RedisLockTypeEnum typeEnum();
    /**
     * 释放时间，秒 s 单位
     */
    long lockTime() default 30;
}
