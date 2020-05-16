package com.exception;


import cn.hutool.core.exceptions.ValidateException;
import com.dto.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * Description
 * 统一异常处理
 * <p>
 * Version		1.0.0
 *
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    /**
     * 如果抛出的是参数异常，将其当做业务异常处理
     *
     * @return
     */
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.OK)
//    Result handlerMethodArgumentNotValid(HttpServletRequest request, MethodArgumentNotValidException re) {
//        StringBuilder sb = new StringBuilder();
//
//        re.getBindingResult().getAllErrors().forEach(error -> {
//            String fieldName = ((FieldError) error).getField();
//            sb.append("[").append(fieldName).append("]").append(error.getDefaultMessage()).append(";    ");
//        });
//        String str = StrUtil.format("参数未通过校验:{}", sb.toString());
//        return Result.errorByParamsVerifyFail(str);
//    }
//
//    /**
//     * 如果抛出的是分布式锁异常，将其当做业务异常处理
//     *
//     * @return
//     */
//    @ExceptionHandler(DistributedLockedException.class)
//    @ResponseStatus(HttpStatus.OK)
//    Result distributedLockedException(HttpServletRequest request, DistributedLockedException re) {
//        return Result.errorByCode(ResultEnum.DISTRIBUTED_LOCKED.getCode(), re.getMessage());
//    }
//
//
//    /**
//     * 如果抛出的是带状态码的异常，将其当做业务异常处理
//     *
//     * @return
//     */
//    @ExceptionHandler(StatefulException.class)
//    @ResponseStatus(HttpStatus.OK)
//    Result handlerState(HttpServletRequest request, StatefulException re) {
//        return Result.errorByCode(re.getStatus(), re.getMessage());
//    }


    /**
     * 如果抛出的是校验异常，将其当做业务异常处理
     *
     * @return
     */
    @ExceptionHandler(ValidateException.class)
    @ResponseStatus(HttpStatus.OK)
    Result handlerBusError(HttpServletRequest request, ValidateException re) {
        return Result.errorByBus(re.getMessage());
    }

    /**
     * 如果抛出的是Exception 指定为系统异常
     *
     * @return
     */
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    Result handleException(Exception e) {
//        logger.error("系统异常,请稍后重试!详细错误信息为：{}", e.getMessage(), e);
//        return Result.errorBySys("系统异常,请稍后重试!详细错误信息为：" + e.getMessage());
//    }
}
