package la.xiong.androidquick.demo.features.architecture.segment.type3;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.FrameLayout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.androidwind.annotation.annotation.BindTag;
import com.androidwind.annotation.annotation.TagInfo;

import java.lang.ref.WeakReference;

import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseActivity;
import la.xiong.androidquick.tool.LogUtil;
import la.xiong.androidquick.tool.StringUtil;

/**
 * @author ddnoshV
 * @website http://blog.csdn.net/ddnosh
 */
@BindTag(type = TagInfo.Type.ACTIVITY, tags = {"web", "网络", "网页", "js", "javascript"}, description = "WebView + JavaScript实例")
public class WebViewJavascriptActivity extends BaseActivity implements JsInterface, LoadJsCallback {

    private InternalWebView mContentWv;
    protected FrameLayout mWebViewContainer;

    public static final String URL = "https://github.com/ddnosh";

    @NonNull
    protected JsApiRegisterFactory mJsApiRegisterFactory;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_webviewjavascript;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        initData();
    }

    @SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface"})
    public void initData() {

        initWebView();
        loadUrl("https://www.baidu.com");
        mJsApiRegisterFactory = JsApiRegisterFactory.newInstance(this);
        mJsApiRegisterFactory.register(new DemoHandler());
    }

    private void initWebView() {
        mWebViewContainer = findViewById(R.id.webview_container);
        mContentWv = new InternalWebView(getApplicationContext(), this);
        mContentWv.addJavascriptInterface(new JavaScriptInterface(mContentWv), "AndroidJSInterfaceV2");
        mWebViewContainer.addView(mContentWv);
    }

    @Override
    protected boolean isLoadDefaultTitleBar() {
        return true;
    }

    //系统自带监听方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Override
    public void onBackPressed() {
        if (mContentWv.canGoBack()) {
            mContentWv.goBack();
            return;
        }

        super.onBackPressed();
    }

    //类相关监听<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Override
    protected void onPause() {
        super.onPause();
        mContentWv.onPause();
    }

    @Override
    protected void onResume() {
        mContentWv.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mJsApiRegisterFactory.release();
        if (mContentWv != null) {
            mContentWv.destroy();
            mContentWv = null;
        }
        super.onDestroy();
    }

    @Override
    public void loadJavaScript(String js) {
        LogUtil.d(TAG, "loadJavaScript:" + js);
        if (mContentWv != null) {
            if (Build.VERSION.SDK_INT <= 18) {
                mContentWv.loadUrl(js);
            } else {
                try {//修复山寨机崩溃，Report ID：#fbbe67f0-6a0c-3c9a-9ea8-10f77db9b6bd
                    mContentWv.evaluateJavascript(js, null);
                } catch (Exception e) {
                    LogUtil.e(TAG, String.valueOf(e));
                    LogUtil.i(TAG, "switch to call loadUrl");
                    mContentWv.loadUrl(js);
                }
            }
        }
    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public WebView getCurrentWebView() {
        return mContentWv;
    }

    @Override
    public void invoke(String name, JSONObject request) {
        LogUtil.i(TAG, String.format("js invoke name:%s, request:%s", name, request));

        JsApiHandler handler = mJsApiRegisterFactory.getHandler(name);
        if (null == handler) {
            LogUtil.e(TAG, "can not find out handler for name:" + name);
            return;
        }

        handler.handle(request.get("params"), request.getString("callback"), false);
    }

    protected void loadUrl(final String url) {
        LogUtil.d(TAG, "loadUrl:" + url);
        if (null != mContentWv) {
            mContentWv.loadUrl(url);
        }
    }

    private String INVOKE_WEB_METHOD = "javascript:try{window.ApiCore.invokeWebMethod('%s', %s)}catch(e){if(console)console.log(e)}";

    public class DemoHandler extends BaseJsApiHandler<JSONObject> {

        @Override
        public String name() {
            return "demoService";
        }

        public String buildJSString(String func, JsResponse<JSONObject> jsResponse) {
            String invokeStr = String.format(INVOKE_WEB_METHOD, func, jsResponse.getData().get("returnValue"));
            LogUtil.d(TAG, "loadJs:" + invokeStr);
            return invokeStr;
        }

        @Override
        protected void handelInBackground(JsRequest<JSONObject> jsRequest) {
            String requestString = jsRequest.getParams().toString();
            JSONObject jsonObjectRequest = JSON.parseObject(requestString);
            String name = jsonObjectRequest.getString("name");
            LogUtil.d(TAG, "name:" + name);
            JSONObject jsonObjectResponse = new JSONObject();
            if ("isLogined".equals(name)) {
                jsonObjectResponse.put("returnValue", "'" + "true" + "'");
                setResponse(jsRequest, JsResponse.success(jsonObjectResponse));
            }
        }
    }

    public class JavaScriptInterface {

        private WeakReference<WebView> webViewHolder = null;
        private Handler mtHandler = new Handler(Looper.getMainLooper());

        public JavaScriptInterface(WebView view) {
            if (view != null) {
                webViewHolder = new WeakReference<WebView>(view);
            }
        }

        @TargetApi(11)
        public void release() {
            if (webViewHolder != null) {
                WebView webView = webViewHolder.get();
                if (webView != null) {
                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
                        webView.removeJavascriptInterface("AndroidJSInterfaceV2");
                    }
                }
            }
        }

        /*
        * JS call Android
        * web端API将此方法包装为 invokeClientMethod
        * */
        @JavascriptInterface
        public String invoke(final String module, final String name, final String parameters, final String callback) {
            LogUtil.d(TAG, "invoke from url --> " + "module:" + module + "; name:" + name + "; parameters:" + parameters + "; callback:" + callback);
            JsApiHandler handler = mJsApiRegisterFactory.getHandler("demoService");
            if (null == handler) {
                LogUtil.e(TAG, "can not find out handler for name:" + name);
                return "{}";
            }
            JSONObject jsonObject = new JSONObject();
            if (!StringUtil.isEmpty(name))
                jsonObject.put("name", name);
            if (!StringUtil.isEmpty(parameters))
                jsonObject.put("parameters", parameters);
            handler.handle(jsonObject, callback, false);
            return "{}";
        }

    }
}
