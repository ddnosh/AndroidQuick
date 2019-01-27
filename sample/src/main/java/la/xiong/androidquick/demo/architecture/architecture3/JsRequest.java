package la.xiong.androidquick.demo.architecture.architecture3;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

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
    private Runnable mJsRequestRunnable;
    @Nullable
    private Runnable mainThreadCallbackRunnable;

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

    void setJsRequestRunnable(@NonNull Runnable jsRequestRunnable) {
        mJsRequestRunnable = jsRequestRunnable;
    }

    void setMainThreadCallbackRunnable(@NonNull Runnable mainThreadCallbackRunnable) {
        this.mainThreadCallbackRunnable = mainThreadCallbackRunnable;
    }

    @Nullable
    Runnable getJsRequestRunnable() {
        return mJsRequestRunnable;
    }

    @Nullable
    Runnable getMainThreadCallbackRunnable() {
        return mainThreadCallbackRunnable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JsRequest<?> request = (JsRequest<?>) o;

        if (params != null ? !params.equals(request.params) : request.params != null) return false;
        if (mJsCallback != null ? !mJsCallback.equals(request.mJsCallback) : request.mJsCallback != null)
            return false;
        if (mJsResponse != null ? !mJsResponse.equals(request.mJsResponse) : request.mJsResponse != null)
            return false;
        if (mJsRequestRunnable != null ? !mJsRequestRunnable.equals(request.mJsRequestRunnable) : request.mJsRequestRunnable != null)
            return false;
        return mainThreadCallbackRunnable != null ? mainThreadCallbackRunnable.equals(request.mainThreadCallbackRunnable) : request.mainThreadCallbackRunnable == null;
    }

    @Override
    public int hashCode() {
        int result = params != null ? params.hashCode() : 0;
        result = 31 * result + (mJsCallback != null ? mJsCallback.hashCode() : 0);
        result = 31 * result + (mJsResponse != null ? mJsResponse.hashCode() : 0);
        result = 31 * result + (mJsRequestRunnable != null ? mJsRequestRunnable.hashCode() : 0);
        result = 31 * result + (mainThreadCallbackRunnable != null ? mainThreadCallbackRunnable.hashCode() : 0);
        return result;
    }
}
