package com.codemind.common.exception.handler;


import com.codemind.common.exception.BusinessException;
import com.codemind.common.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(BusinessException.class)
    public Result<?> handleBusinessException(BusinessException e){

        return Result.error(e.getMessage());

    }

}