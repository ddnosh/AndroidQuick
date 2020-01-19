package com.androidwind.androidquick.demo.features.architecture.segment.type3;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

import androidx.annotation.Nullable;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class JsResponse<T> {

    private int code;

    private String msg;

    private T data;

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

    @Nullable
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @JSONField(serialize = false, deserialize = false)
    public boolean isSuccess() {
        return code == 0;
    }

    public static <T> JsResponse<T> success(T data) {
        JsResponse<T> response = new JsResponse<>();
        response.data = data;
        return response;
    }

    public static <T> JsResponse<T> error(int code, String msg) {
        JsResponse<T> response = new JsResponse<>();
        response.code = code;
        response.msg = msg;
        return response;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
