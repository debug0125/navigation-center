package com.pzc.navigationweb.constant.enumtype;

/**
 * @author ryf
 * @date 8/13/21 1:28 PM
 */
public enum RedisLockTypeEnum {
    /**
     * 自定义 key 前缀
     */
    ONE("Business1", "Test1"),

    TWO("Business2", "Test2");
    private String code;
    private String desc;
    RedisLockTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    public String getCode() {
        return code;
    }
    public String getDesc() {
        return desc;
    }
    public String getUniqueKey(String key) {
        return String.format("%s:%s", this.getCode(), key);
    }

    public static void main(String[] args) {
        RedisLockTypeEnum typeEnum = ONE;
        System.out.println(typeEnum.getUniqueKey("ryf"));
    }
}
