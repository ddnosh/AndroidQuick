package la.xiong.androidquick.demo.architecture.architecture3;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import la.xiong.androidquick.task.Task;
import la.xiong.androidquick.task.TaskScheduler;
import la.xiong.androidquick.tool.LogUtil;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */

public abstract class BaseJsApiHandler<T> implements JsApiHandler {

    private static final String TAG = "BaseJsApiHandler";

    private Task task;
    @Nullable
    private LoadJsCallback mLoadJsCallback;

    private boolean isReleased;

    @NonNull
    private final List<JsRequest> mRunningRequestMap = new ArrayList<>();

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

    protected abstract void handelInBackground(JsRequest<T> jsRequest);

    protected void onHandleFinish(JsRequest<T> jsRequest) {
        if (null != jsRequest && !TextUtils.isEmpty(jsRequest.getJsCallback())
                && null != jsRequest.getJsResponse()) {
            loadJsFunc(jsRequest.getJsCallback(), jsRequest.getJsResponse());
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
    public void handle(@Nullable final Object params, final String jsCallback) {
        if (isReleased) {
            LogUtil.i(TAG, "has released, does not handle call.");
            return;
        }

        final JsRequest<T> request = new JsRequest<>();
        request.setParams(params);
        request.setJsCallback(jsCallback);
        request.setJsRequestRunnable(new Runnable() {
            @Override
            public void run() {
                handelInBackground(request);
            }
        });
        request.setMainThreadCallbackRunnable(new Runnable() {
            @Override
            public void run() {
                removeJsRequest(request);
                onHandleFinish(request);
            }
        });

        mRunningRequestMap.add(request);

        task = new Task<String>() {

            @Override
            public String doInBackground()  {
                handelInBackground(request);
                return "background task";
            }

            @Override
            public void onSuccess(String result) {
                //回调到主线程
                removeJsRequest(request);
                onHandleFinish(request);
            }

            @Override
            public void onFail(Throwable throwable) {
                super.onFail(throwable);
                //doInBackground 里发生错误时回调
            }

            @Override
            public void onCancel() {
                super.onCancel();
                //任务被取消时回调
            }
        };
        TaskScheduler.execute(task);
    }

    @Override
    public void release() {
        isReleased = true;

        TaskScheduler.cancelTask(task);

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
        sb.append(String.format("'%s'", JsonToObject.toJsonString(jsResponse)));
        sb.append(")");

        String js = sb.toString();

        LogUtil.d(TAG, "loadJs:" + js);
        return js;
    }

}
