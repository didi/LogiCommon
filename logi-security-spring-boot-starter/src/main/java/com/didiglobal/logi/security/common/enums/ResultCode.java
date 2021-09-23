package com.didiglobal.logi.security.common.enums;

import com.didiglobal.logi.security.exception.CodeMsg;
import io.swagger.annotations.ApiModel;

/**
 * 规定: #1表示成功
 * #1001～1999 区间表示参数错误
 * #2001～2999 区间表示用户错误
 * #3001～3999 区间表示接口异常
 */
@ApiModel(value = "返回说明")
public enum ResultCode implements CodeMsg {
    /* 成功 */
    SUCCESS(200, "成功"),

    /* 默认失败 */
    COMMON_FAIL(999, "失败"),

    /* 参数错误：1000～1999 */
    PARAM_NOT_VALID(1001, "参数无效"),
    PARAM_IS_BLANK(1002, "参数为空"),
    PARAM_ID_IS_BLANK(1003, "参数id为空"),
    PARAM_TYPE_ERROR(1004, "参数类型错误"),
    PARAM_NOT_COMPLETE(1005, "参数缺失"),
    PARAM_LENGTH_ERROR(1006, "参数长度不正确"),

    /* 用户错误 */
    USER_NOT_LOGIN(2001, "用户未登录"),
    USER_ACCOUNT_EXPIRED(2002, "账号已过期"),
    USER_CREDENTIALS_ERROR(2003, "密码错误"),
    USER_CREDENTIALS_EXPIRED(2004, "密码过期"),
    USER_ACCOUNT_DISABLE(2005, "账号不可用"),
    USER_ACCOUNT_LOCKED(2006, "账号被锁定"),
    USER_ACCOUNT_NOT_EXIST(2007, "账号不存在"),
    USER_ACCOUNT_ALREADY_EXIST(2008, "账号已存在"),
    USER_ACCOUNT_USE_BY_OTHERS(2009, "账号下线"),
    USER_ACCOUNT_INSERT_FAIL(2010, "注册失败"),
    USER_PHONE_EXIST(2011, "手机号已存在"),
    EMAIL_FORMAT_ERROR(2012, "邮箱格式错误"),
    USER_EMAIL_EXIST(2013, "邮箱已存在"),
    USER_PASSWORD_DECRYPT_ERROR(2014, "密码解密出错"),
    USER_ID_CANNOT_BE_NULL(2015, "用户id不可为空"),
    USER_NOT_EXISTS(2016, "用户不存在"),

    /* 业务错误 */
    NO_PERMISSION(3001, "没有权限"),

    /* 角色错误 */
    ROLE_BUSINESS_ERROR(4001, "内部错误"),
    ROLE_NOT_EXISTS(4002, "角色不存在"),
    ROLE_USER_AUTHED(4003, "有用户已绑定该角色"),
    ROLE_NAME_ALREADY_EXISTS(4004, "角色名已存在"),
    ROLE_NAME_CANNOT_BE_BLANK(4005, "角色名不可为空"),
    ROLE_DEPT_CANNOT_BE_BLANK(4006, "角色描述不可为空"),
    ROLE_PERMISSION_CANNOT_BE_NULL(4007, "角色权限不可为空"),
    ROLE_ASSIGN_FLAG_IS_NULL(4008, "角色分配flag不可为空"),
    ROLE_ID_CANNOT_BE_NULL(4009, "角色id不可为空"),

    /* 项目错误 */
    PROJECT_NAME_ALREADY_EXISTS(5001, "项目名已存在"),
    PROJECT_NOT_EXISTS(5002, "项目不存在"),
    PROJECT_UN_RUNNING(5003, "项目未运行"),
    PROJECT_ID_CANNOT_BE_NULL(5004, "项目id不可为空"),
    PROJECT_NAME_CANNOT_BE_BLANK(5005, "项目名不可为空"),
    PROJECT_DES_CANNOT_BE_BLANK(5006, "项目描述不可为空"),
    PROJECT_DEPT_CANNOT_BE_NULL(5007, "项目使用部门不可为空"),
    PROJECT_CHARGE_USER_CANNOT_BE_NULL(5008, "项目负责人不可为空"),

    /* 操作日志错误 */
    OPLOG_NOT_EXIST(6001, "操作日志不存在"),

    /* 消息错误 */
    MESSAGE_NOT_EXIST(7001, "消息不存在"),

    /* 页面权限错误 */
    PERMISSION_DATA_ERROR(8001, "获取权限数据异常"),

    /* 部门错误 */
    DEPT_DATA_ERROR(9001, "获取部门数据异常，请检查部门表数据"),

    /* 资源权限管理错误 */
    RESOURCE_ASSIGN_ERROR(10001, "资源权限分配异常，具体资源id不为null，则资源类别id不可为null"),
    RESOURCE_ASSIGN_ERROR_2(10002, "资源权限分配异常，资源类别id不为null，则项目id不可为null"),
    RESOURCE_INVALID_SHOW_LEVEL(10003, "请输入有效的展示级别（1 <= showLevel <= 3）"),
    RESOURCE_SHOW_LEVEL_ERROR(10004, "2级展示级别，项目id不可为空"),
    RESOURCE_SHOW_LEVEL_ERROR_2(10005, "3级展示级别，项目id或资源类别id不可为null"),
    RESOURCE_ASSIGN_BATCH_FLAG_CANNOT_BE_NULL(10006, "资源权限批量分配的标识不可为空"),
    RESOURCE_INVALID_CONTROL_LEVEL(10007, "请输入有效的资源权限控制级别（1 <= controlLevel <= 2）"),
    RESOURCE_TYPE_ID_CANNOT_BE_NULL(10008, "资源类别id不可为空"),
    RESOURCE_ID_CANNOT_BE_NULL(10009, "具体资源id不可为空"),
    RESOURCE_TYPE_NOT_EXISTS(10010, "资源类别不存在")
    ;

    private final Integer code;

    private final String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    /**
     * 根据code获取message.
     *
     * @param code taskCode
     * @return str
     */
    public static String getMessageByCode(Integer code) {
        for (ResultCode ele : values()) {
            if (ele.getCode().equals(code)) {
                return ele.getMessage();
            }
        }
        return null;
    }
}
