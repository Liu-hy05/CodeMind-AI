package com.codemind.common.exception.handler;

import com.codemind.common.exception.BusinessException;
import com.codemind.common.result.Result;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public Result<?> handleBusinessException(BusinessException e) {

        return Result.error(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {

        FieldError fieldError =
                e.getBindingResult().getFieldError();

        String message = fieldError == null
                ? "请求参数错误"
                : fieldError.getDefaultMessage();

        return Result.error(400, message);
    }
}