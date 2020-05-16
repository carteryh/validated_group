package com.dto;

import com.enums.ResultEnum;
import lombok.Data;

/**
 * Description
 * Rest接口返回的结果
 * <p>
 * Version		1.0.0
 *
 * @author sakuno <p>
 * Date	     2019/10/29 16:46
 */
@Data
public class Result<T> {
    /**
     * 错误码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 内容
     */
    private T data;

    /**
     * 入参校验失败
     *
     * @param errorMsg
     * @return
     */
    public static Result errorByParamsVerifyFail(String errorMsg) {
        Result t = new Result<>();
        t.setCode(ResultEnum.PARAMS_VERIFY_FAIL.getCode());
        t.setMsg(errorMsg);
        return t;
    }

    /**
     * 自定义code异常，请确认code在@see ResultEnum中
     *
     * @param code     自定义的code
     * @param errorMsg 错误消息
     * @return 结果
     */
    public static Result errorByCode(int code, String errorMsg) {
        Result t = new Result<>();
        t.setCode(code);
        t.setMsg(errorMsg);
        return t;
    }

    /**
     * 业务处理异常
     *
     * @param errorMsg
     * @return
     */
    public static Result errorByBus(String errorMsg) {
        Result t = new Result<>();
        t.setCode(ResultEnum.BUS_ERROR.getCode());
        t.setMsg(errorMsg);
        return t;
    }

    /**
     * 系统异常
     *
     * @param errorMsg
     * @return
     */
    public static Result errorBySys(String errorMsg) {
        Result t = new Result<>();
        t.setCode(ResultEnum.SYS_ERROR.getCode());
        t.setMsg(errorMsg);
        return t;
    }

    /**
     * 失败
     *
     * @param errorMsg 失败信息
     * @return
     */
    public static Result fail(String errorMsg) {
        Result t = new Result<>();
        t.setCode(ResultEnum.FAIL.getCode());
        t.setMsg(errorMsg);
        return t;
    }

    /**
     * 不带参数的成功
     *
     * @return
     */
    public static Result success() {
        return success(null);
    }

    /**
     * 成功
     *
     * @param data 要返回的数据
     * @param <T>  要返回的类型
     * @return
     */
    public static <T> Result<T> success(T data) {
        Result<T> t = new Result<>();
        t.setData(data);
        t.setCode(ResultEnum.SUCCESS.getCode());
        return t;
    }
}
