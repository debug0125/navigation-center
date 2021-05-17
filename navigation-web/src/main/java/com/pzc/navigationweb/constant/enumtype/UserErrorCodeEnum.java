package com.pzc.navigationweb.constant.enumtype;

/**
 * @author ryf
 * @date 5/17/21 4:12 PM
 */
public enum UserErrorCodeEnum {
    ILLGAL_ARGUMENT("ILLGAL_ARGUMENT", "非法参数"),
    IDEMPOTENT_INVOKE("IDEMPOTENT_INVOKE", "幂等异常"),
    EXCUTE_SQL_ERROR("EXCUTE_SQL_ERROR", "执行数据库错误"),
    NOT_FIND_USER("NOT_FIND_USER", "找不到用户"),
    ACCOUNT_IS_NULL("ACCOUNT_IS_NULL", "账户为空"),
    PASSWORD_IS_NULL("PASSWORD_IS_NULL", "密码为空"),
    PASSWORD_CHECK_ERROR("PASSWORD_CHECK_ERROR", "密码验证错误"),
    LOGINTIME_IS_NULL("LOGINTIME_IS_NULL", "登录时间空"),
    LOGINT_OUT_TIME("LOGINT_OUT_TIME", "登录超时"),
    AES_DECODE_ERROR("AES_DECODE_ERROR", "解码异常"),
    LOGINFAILTIME_IS_MAX("LOGINFAILTIME_IS_MAX", "登录失败超过平台限定"),
    REQUEST_TOKEN("REQUEST_TOKEN", "请求的token为空"),
    USER_ALREADY_EXISTS("USER_ALREADY_EXISTS", "用户已经存在"),
    USER_ISFREEZE("USER_ISFREEZE", "用户已被冻结"),
    USER_UN_PAYMENT_ON_DELIVERY("USER_UN_PAYMENT_ON_DELIVERY", "用户不能货到付款"),
    USER_ADD_FAILL("USER_ADD_FAILL", "用户添加失败"),
    USER_DELLETE_FAILL("USER_DELLETE_FAILL", "用户删除失败"),
    USER_ID_ISNULL("USER_ID_ISNULL", "用户ID是空"),
    USER_MODIFY_BY_ISNULL("USER_MODIFY_BY_ISNULL", "修改人是空"),
    FAILURE_TO_GET_LOGIN_USER("FAILURE_TO_GET_LOGIN_USER", "获取登陆用户失败"),
    USER_CREATE_BY_ISNULL("USER_CREATE_BY_ISNULL", "创建人是空"),
    USER_UPDATE_ERROR("USER_UPDATE_ERROR", "修改用户信息失败"),
    USER_PASSWORD_UPDATE_ERROR("USER_PASSWORD_UPDATE_ERROR", "修改用户密码失败"),
    MD5_ENCRYPTION_EXCEPTION("USER_PASSWORD_UPDATE_ERROR", "md5加密错误");

    private String errMsg;
    private String errCode;

    private UserErrorCodeEnum(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    private UserErrorCodeEnum(String errCode) {
        this.errCode = errCode;
    }

    public static UserErrorCodeEnum getTbcpErrorCodeEnum(String code) {
        UserErrorCodeEnum[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            UserErrorCodeEnum x = var1[var3];
            if (x.getErrCode().equals(code)) {
                return x;
            }
        }

        return null;
    }

    public String getErrCode() {
        return this.errCode;
    }

    public String getErrMsg() {
        return this.errMsg;
    }
}
