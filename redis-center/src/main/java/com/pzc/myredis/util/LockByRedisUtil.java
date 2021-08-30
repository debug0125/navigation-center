package com.pzc.myredis.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author ryf
 * @date 8/6/21 11:07 AM
 */

@Component
public class LockByRedisUtil {

    private static RedisTemplate redisTemplate;

    public LockByRedisUtil() {
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        LockByRedisUtil.redisTemplate = redisTemplate;
    }

    public static void deleteKey(String key) {
        redisTemplate.execute((RedisCallback) (redisConnection) -> {
            return redisConnection.del(new byte[][]{key.getBytes()});
        });
    }

    public static boolean isLocked(String key) {
        return (Boolean)redisTemplate.execute((RedisCallback) (connection) -> {
            new Operation();
            boolean result = false;
            long expireAt = System.currentTimeMillis();
            Boolean acquire = connection.setNX(key.getBytes(), String.valueOf(expireAt).getBytes());
            if (acquire) {
                result = true;
            } else {
                byte[] value = connection.get(key.getBytes());
                if (Objects.nonNull(value) && value.length > 0) {
                    long expireTime = Long.parseLong(new String(value));
                    result = expireTime < System.currentTimeMillis();
                }
            }

            return result;
        });
    }

    public static LockByRedisUtil.Operation lockExecute(ExecuterTask executerTask, String key, long expireMillis, boolean isFinishDelLock) {
        return (LockByRedisUtil.Operation)redisTemplate.execute((RedisCallback) (connection) -> {
            Operation operation = new Operation();
            boolean result = false;
            long expireAt = System.currentTimeMillis() + expireMillis;
            Boolean acquire = connection.setNX(key.getBytes(), String.valueOf(expireAt).getBytes());
            if (acquire) {
                result = true;
            } else {
                byte[] value = connection.get(key.getBytes());
                if (Objects.nonNull(value) && value.length > 0) {
                    long expireTime = Long.parseLong(new String(value));
                    if (expireTime < System.currentTimeMillis()) {
                        byte[] oldValue = connection.getSet(key.getBytes(), String.valueOf(System.currentTimeMillis() + expireMillis).getBytes());
                        result = Long.parseLong(new String(oldValue)) < System.currentTimeMillis();
                    }
                }
            }

            if (result) {
                try {
                    operation.setOperationEnum(OperationEnum.EXECUTE);
                    operation.setResult(executerTask.execute());
                } finally {
                    if (isFinishDelLock) {
                        connection.del(new byte[][]{key.getBytes()});
                    }

                }
            } else {
                operation.setOperationEnum(OperationEnum.NOT_EXECUTE);
            }

            return operation;
        });
    }

    public static enum OperationEnum {
        NOT_EXECUTE("未执行"),
        EXECUTE("已执行");

        private String desc;

        private OperationEnum(String desc) {
            this.desc = desc;
        }
    }

    public static class Operation<T> {
        private LockByRedisUtil.OperationEnum operationEnum;
        private T result;

        public Operation() {
        }

        public LockByRedisUtil.OperationEnum getOperationEnum() {
            return this.operationEnum;
        }

        public void setOperationEnum(LockByRedisUtil.OperationEnum operationEnum) {
            this.operationEnum = operationEnum;
        }

        public boolean isExecuted() {
            return this.operationEnum == LockByRedisUtil.OperationEnum.EXECUTE;
        }

        public T getResult() {
            return this.result;
        }

        public void setResult(T result) {
            this.result = result;
        }
    }
}
