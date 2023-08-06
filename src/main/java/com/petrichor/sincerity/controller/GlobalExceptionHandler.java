package com.petrichor.sincerity.controller;

import com.petrichor.sincerity.api.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    public Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }

    @ExceptionHandler(BadSqlGrammarException.class)
    public CommonResult<String> sqlException(Exception e) {
        Logger logger = getLogger(e.getClass());
        String message = e.getMessage();
        logger.error(message);
        String cause = e.getCause().toString();
        String reason = cause.substring(cause.indexOf(":") + 1);
        return CommonResult.failed("数据库错误：" + reason);
    }

    @ExceptionHandler(Exception.class)
    public CommonResult<String> otherException(Exception e) {
        Logger logger = getLogger(e.getClass());
        String message = e.getMessage();
        logger.error(message);
        String cause = e.getCause().toString();
        String reason = cause.substring(cause.indexOf(":") + 1);
        return CommonResult.failed("服务器内部错误：" + reason);
    }
}
