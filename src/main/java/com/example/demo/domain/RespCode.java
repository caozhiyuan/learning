package com.example.demo.domain;

/**
 * Created by admin on 2018-06-23.
 */
public enum RespCode {
    BIZ(1, "业务异常"),
    SUCCESS(0, "请求成功"),
    SYSTEMERROR(-1, "系统异常，请稍后重试"),
    NOTFOUND(-2, "请求地址不存在");

    private int code;
    private String msg;

    RespCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
}