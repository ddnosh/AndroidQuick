package com.androidwind.androidquick.demo.features.module.network.retrofit.network3;

import java.io.Serializable;
import java.util.List;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class XHYRes<T> implements Serializable {

    private int error_code;
    private String reason;
    private int total;
    private List<T> result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}
