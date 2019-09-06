package com.androidwind.androidquick.demo.features.architecture.segment.type3;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.androidwind.task.AdvancedTask;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class JsRequest<T> {

    @Nullable
    private Object params;
    @Nullable
    private String mJsCallback;
    @Nullable
    private JsResponse<T> mJsResponse;
    @Nullable
    private AdvancedTask mTask;
    @Nullable
    private boolean isPending;

    @Nullable
    public Object getParams() {
        return params;
    }

    public void setParams(@Nullable Object params) {
        this.params = params;
    }

    @Nullable
    public String getJsCallback() {
        return mJsCallback;
    }

    void setJsCallback(@Nullable String jsCallback) {
        mJsCallback = jsCallback;
    }

    @Nullable
    JsResponse<T> getJsResponse() {
        return mJsResponse;
    }

    void setJsResponse(@NonNull JsResponse<T> jsResponse) {
        mJsResponse = jsResponse;
    }

    @Nullable
    public AdvancedTask getTask() {
        return mTask;
    }

    public void setTask(@Nullable AdvancedTask mTask) {
        this.mTask = mTask;
    }

    @Nullable
    public boolean isPending() {
        return isPending;
    }

    public void setPending(@Nullable boolean pending) {
        isPending = pending;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JsRequest<?> request = (JsRequest<?>) o;

        if (params != null ? !params.equals(request.params) : request.params != null) return false;
        if (mJsCallback != null ? !mJsCallback.equals(request.mJsCallback) : request.mJsCallback != null) {
            return false;
        }
        if (mJsResponse != null ? !mJsResponse.equals(request.mJsResponse) : request.mJsResponse != null) {
            return false;
        }
        return mTask != null ? !mTask.equals(request.mTask) : request.mTask != null;
    }

    @Override
    public int hashCode() {
        int result = params != null ? params.hashCode() : 0;
        result = 31 * result + (mJsCallback != null ? mJsCallback.hashCode() : 0);
        result = 31 * result + (mJsResponse != null ? mJsResponse.hashCode() : 0);
        result = 31 * result + (mTask != null ? mTask.hashCode() : 0);
        return result;
    }
}
