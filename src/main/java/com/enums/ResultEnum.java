package com.enums;

/**
 * Description
 * 返回的结果枚举
 * <p>
 * 错误区间必须是4位，中间进行具体区分，不相干的错误请勿贴着添加。
 * Version		1.0.0
 *
 * @author sakuno <p>
 * Date	     2019/11/6 11:44
 */
public enum ResultEnum {
    /**
     * 成功
     */
    SUCCESS(0, "成功"),
    /**
     * 失败
     */
    FAIL(2000, "失败"),

    ENUM_CODE_NOT_EXIST(2001, "对应的枚举不存在"),
    /**
     * 系统异常
     */
    SYS_ERROR(2100, "系统异常"),
    /**
     * 业务处理异常
     */
    BUS_ERROR(2200, "业务处理异常"),
    /**
     * 分布式锁被抢占异常
     */
    DISTRIBUTED_LOCKED(2201, "分布式锁已被抢占"),
    /**
     * 入参校验失败
     */
    PARAMS_VERIFY_FAIL(2300, "入参校验未通过"),
    /**
     * 开户失败
     */
    OPEN_FAIL(2400, "开户失败"),
    /**
     * 账户已存在
     */
    ACCOUNT_HAS_OPEN(2401, "账户已存在"),

    /**
     * 额度已过期
     */
    CREDIT_EXPIRED(2500, "额度已过期"),
    /**
     * 冻结额度不足
     */
    CREDIT_NOT_ENOUGH_FREEZE(2501, "冻结额度不足"),
    /**
     * 可用额度不足
     */
    CREDIT_NOT_ENOUGH_AVAILABLE(2502, "可用额度不足"),
    /**
     * 已用额度不足
     */
    CREDIT_NOT_ENOUGH_USED(2503, "已用额度不足"),
    /**
     * 额度不一致
     */
    CREDIT_NOT_EQ(2504, "额度不一致"),

    /**
     * 对应业务流水不存在
     */
    RECORD_NOT_EXIST(2600, "对应业务记录不存在"),
    /**
     * 冻结流水已存在
     */
    RECORD_EXIST_FREEZE(2601, "业务冻结记录已存在"),
    /**
     * 业务解冻记录已存在
     */
    RECORD_EXIST_UNFREEZE(2602, "业务解冻记录已存在"),
    /**
     * 业务使用记录已存在
     */
    RECORD_EXIST_USED(2603, "业务使用记录已存在"),

    /**
     * 额度变更失败
     */
    UPDATE_CREDIT_FAIL(2700, "额度变更失败"),

    /**
     * 释放超额
     */
    CREDIT_EXCEED_RELEASE(2800, "超额释放"),
    ;
    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
