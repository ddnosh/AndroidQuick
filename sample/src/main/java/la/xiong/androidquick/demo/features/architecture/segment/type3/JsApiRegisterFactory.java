package la.xiong.androidquick.demo.features.architecture.segment.type3;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class JsApiRegisterFactory {

    private final Map<String, JsApiHandler> mApiHandlerMap = new HashMap<>();
    @Nullable
    private LoadJsCallback mLoadJsCallback;

    public static JsApiRegisterFactory newInstance(LoadJsCallback callback) {
        return new JsApiRegisterFactory(callback);
    }

    public void release() {
        mApiHandlerMap.clear();
        setLoadJsCallback(null);
    }

    public void register(JsApiHandler apiHandler) {
        if (null != apiHandler) {
            apiHandler.setLoadJsCallback(mLoadJsCallback);
            mApiHandlerMap.put(apiHandler.name(), apiHandler);
        }
    }

    public void unregister(String name) {
        if (!TextUtils.isEmpty(name) && mApiHandlerMap.containsKey(name)) {
            mApiHandlerMap.remove(name);
        }
    }

    @Nullable
    public JsApiHandler getHandler(String name) {
        return mApiHandlerMap.get(name);
    }


    private void setLoadJsCallback(@Nullable LoadJsCallback callback) {
        mLoadJsCallback = callback;
    }

    private JsApiRegisterFactory(LoadJsCallback callback) {
        setLoadJsCallback(callback);
    }

}
