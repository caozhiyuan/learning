package com.example.demo.exception;

import com.example.demo.domain.RespCode;

public class BizException extends RuntimeException {

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    private int code;
    private String msg;

    public BizException(int code, String message) {
        this.code = code;
        this.msg = message;
    }

    public BizException(String message) {
        this.code = RespCode.BIZ.getCode();
        this.msg = message;
    }
}
