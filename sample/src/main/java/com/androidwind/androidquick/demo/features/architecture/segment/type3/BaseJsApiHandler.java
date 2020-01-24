package com.androidwind.androidquick.demo.features.architecture.segment.type3;

import android.app.Activity;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;

import com.alibaba.fastjson.JSON;
import com.androidwind.task.Task;
import com.androidwind.task.TinyTaskExecutor;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public abstract class BaseJsApiHandler<T> implements JsApiHandler {

    private static final String TAG = "BaseJsApiHandler";

    @Nullable
    protected LoadJsCallback mLoadJsCallback;

    private boolean isReleased;

    @NonNull
    private final List<JsRequest> mRunningRequestMap = new ArrayList<>();

    public BaseJsApiHandler() {
    }

    @Nullable
    public Activity getActivity() {
        LoadJsCallback callback = mLoadJsCallback;
        if (callback == null) {
            return null;
        }

        if (callback.getContext() != null && callback.getContext() instanceof Activity) {
            return (Activity) callback.getContext();
        }

        return null;
    }

    @Nullable
    public WebView getCurrentWebView() {
        if (mLoadJsCallback == null) {
            return null;
        }
        if (mLoadJsCallback.getCurrentWebView() != null) {
            return mLoadJsCallback.getCurrentWebView();
        }
        return null;
    }

    protected abstract void handelInBackground(JsRequest<T> jsRequest);

    protected void onHandleFinish(final JsRequest<T> jsRequest) {
        if (null != jsRequest && !TextUtils.isEmpty(jsRequest.getJsCallback()) && null != jsRequest.getJsResponse()) {
            if (Looper.myLooper() != Looper.getMainLooper()) {
                TinyTaskExecutor.postToMainThread(new Runnable() {
                    @Override
                    public void run() {
                        onHandleFinish(jsRequest);
                    }
                });
            } else {
                loadJsFunc(jsRequest.getJsCallback(), jsRequest.getJsResponse());
            }
        }
    }

    @Override
    public void setLoadJsCallback(@Nullable LoadJsCallback callback) {
        mLoadJsCallback = callback;
    }

    public void setResponse(JsRequest<T> jsRequest, JsResponse<T> jsResponse) {
        jsRequest.setJsResponse(jsResponse);
    }

    @Override
    public void handle(@Nullable Object params, String jsCallback, boolean isPending) {
        if (isReleased) {
            Log.i(TAG, "has released, does not handle call.");
            return;
        }
        final JsRequest<T> request = new JsRequest<>();
        request.setPending(isPending);
        request.setParams(params);
        request.setJsCallback(jsCallback);
        request.setTask(new Task() {
            @Override
            public Object doInBackground() {
                handelInBackground(request);
                return null;
            }

            @Override
            public void onSuccess(Object o) {
                if (request.isPending()) {
                    return;
                }
                removeJsRequest(request);
                onHandleFinish(request);
            }

            @Override
            public void onFail(Throwable throwable) {

            }
        });
        mRunningRequestMap.add(request);
        TinyTaskExecutor.execute(request.getTask());
    }

    /**
     * 继续执行
     */
    public void handlePendingRequest(JsRequest<T> request) {
        if (request == null) {
            return;
        }
        for (int i = 0; i < mRunningRequestMap.size(); i++) {
            if (mRunningRequestMap.get(i).equals(request)) {
                removeJsRequest(request);
                onHandleFinish(request);
                break;
            }
        }
    }

    @Override
    public void release() {
        isReleased = true;

        for (JsRequest request : mRunningRequestMap) {
            TinyTaskExecutor.removeTask(request.getTask());
        }

        mRunningRequestMap.clear();
    }

    private void removeJsRequest(JsRequest request) {
        mRunningRequestMap.remove(request);
    }

    protected void loadJsFunc(String func, JsResponse<T> jsResponse) {
        if (isReleased || TextUtils.isEmpty(func) || null == jsResponse) {
            return;
        }

        loadJs(buildJSString(func, jsResponse));
    }

    private void loadJs(String js) {
        LoadJsCallback callback = mLoadJsCallback;
        if (null != callback) {
            callback.loadJavaScript(js);
        }
    }

    public String buildJSString(String func, JsResponse<T> jsResponse) {
        StringBuilder sb = new StringBuilder("javascript:");
        sb.append(func);
        sb.append("(");
        sb.append(String.format("'%s'", JSON.toJSONString(jsResponse)));
        sb.append(")");

        String js = sb.toString();

        Log.d(TAG, "loadJs:" + js);
        return js;
    }

}
