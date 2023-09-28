package com.petrichor.sincerity.controller;

import com.petrichor.sincerity.api.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;


@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    public Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }

    @ExceptionHandler(SQLException.class)
    public CommonResult<String> sqlException(SQLException e) {
        Logger logger = getLogger(e.getClass());
        logger.error(String.valueOf(e));
        return CommonResult.failed("数据库错误：" + getReason(e));
    }

    @ExceptionHandler(Exception.class)
    public CommonResult<String> otherException(Exception e) {
        Logger logger = getLogger(e.getClass());
        logger.error(String.valueOf(e));
        return CommonResult.failed("服务器内部错误：" + getReason(e));
    }

    public String getReason(Exception e) {
        String cause = String.valueOf(e.getCause());
        return cause.substring(cause.indexOf(":") + 1);
    }
}
