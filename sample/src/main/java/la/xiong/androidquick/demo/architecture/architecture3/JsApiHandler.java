package la.xiong.androidquick.demo.architecture.architecture3;

import android.support.annotation.Nullable;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */

public interface JsApiHandler {

    String name();

    /**
     * js 调用native的接口
     * @param params
     * @param jsCallback
     */
    void handle(@Nullable Object params, @Nullable String jsCallback);

    void release();

    void setLoadJsCallback(LoadJsCallback callback);
}
