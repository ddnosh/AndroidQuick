package com.androidwind.androidquick.demo.features.architecture.segment.type3;

import android.support.annotation.Nullable;


/**
 * js -> native
 *
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public interface JsApiHandler {

    String name();

    /**
     * js 调用native的接口
     *
     * @param params
     * @param jsCallback
     * @param isPending
     */
    void handle(@Nullable Object params, @Nullable String jsCallback, boolean isPending);

    void release();

    void setLoadJsCallback(LoadJsCallback callback);
}
