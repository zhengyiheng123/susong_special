package com.xyd.susong.base;

import java.io.Serializable;

/**
 * @author: zhaoxiaolei
 * @date: 2017/4/17
 * @time: 14:24
 * @description:
 */

public class BaseModel<T> implements Serializable{

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
    public String getMessage() {
        return null==message?msg:message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    //private Boolean ReState = false;
    private T data;
    private int code;
    private String message;

    public String getMsg() {
        return null==msg?message:msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String msg;
    // private String ReToken;
}
