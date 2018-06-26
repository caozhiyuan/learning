package com.example.demo.config;

import com.example.demo.domain.RespCode;
import com.example.demo.domain.RespEntity;
import com.example.demo.exception.BizException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.OK)
    public RespEntity handleError404(HttpServletRequest request, Exception ex)   {
        return new RespEntity(RespCode.NOTFOUND);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public RespEntity unknownException(HttpServletRequest request, Exception ex) {
        if(ex instanceof BizException) {
            return new RespEntity((BizException)ex);
        }
        return new RespEntity(RespCode.SYSTEMERROR);
    }
}