package com.example.demo.domain;

import com.example.demo.exception.BizException;

import java.io.Serializable;

/**
 * Created by admin on 2018-06-23.
 */
public class RespEntity implements Serializable {
    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private Object data;

    public RespEntity(RespCode respCode) {
        this.code = respCode.getCode();
        this.msg = respCode.getMsg();
    }

    public RespEntity(Object data) {
        this.code = RespCode.SUCCESS.getCode();
        this.data = data;
    }

    public RespEntity(BizException ex) {
        this.code = ex.getCode();
        this.msg = ex.getMsg();
    }
}

