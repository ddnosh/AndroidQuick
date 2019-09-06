package com.androidwind.androidquick.demo.features.module.network.common.http;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public interface HttpCallback {
    void onSuccess(String response);

    void onFail(String response);
}
