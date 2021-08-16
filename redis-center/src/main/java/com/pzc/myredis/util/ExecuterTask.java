package com.pzc.myredis.util;

/**
 * 执行任务
 * @author ryf
 * @date 8/6/21 11:15 AM
 */
@FunctionalInterface
public interface ExecuterTask {
    /**
     * 执行方法
     */
    Object execute();
}
