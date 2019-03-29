package la.xiong.androidquick.demo.architecture.segment.type3;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.Keep;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.alibaba.fastjson.JSONObject;

import la.xiong.androidquick.tool.LogUtil;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class InternalWebView extends WebView {

    private static final String TAG = "InternalWebView";

    private static final String UA_AQ = "androidquick";

    private InternalWebView mContentWv;

    private WebViewListener mWebViewListener;

    public InternalWebView(Context context) {
        super(context);
        init(context);
    }

    public InternalWebView(Context context, JsInterface jsInterface) {
        super(context);
        init(context, jsInterface);
    }


    public InternalWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public InternalWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void setWebChromeClient(WebChromeClient webChromeClient) {
        super.setWebChromeClient(webChromeClient);
    }

    private void init(Context context) {
        init(context, null);
    }

    private void init(Context context, @Nullable JsInterface jsInterface) {
//        File cacheDir = ContextUtil.getWebViewCacheDir();
//        String cachePath = cacheDir.getPath();

        WebSettings webSetting = this.getSettings();
        if (webSetting != null) {
            try {
                webSetting.setDatabaseEnabled(true);
                webSetting.setBuiltInZoomControls(false);
                webSetting.setSupportZoom(false); // 不可以缩放
                webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
                webSetting.setUseWideViewPort(true);
                webSetting.setLoadWithOverviewMode(true);
                webSetting.setJavaScriptEnabled(true);
                webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
                webSetting.setGeolocationEnabled(true);
                webSetting.setAllowFileAccess(false);
                webSetting.setSavePassword(false);

                webSetting.setDomStorageEnabled(true);
                webSetting.setCacheMode(WebSettings.LOAD_DEFAULT);
                webSetting.setAppCacheEnabled(true);//开启Application Caches功能
//                webSetting.setAppCachePath(cachePath);
//                webSetting.setDatabasePath(cachePath);
                webSetting.setUserAgentString(webSetting.getUserAgentString() + " " + UA_AQ);

                // http://www.tangzongchao.com/2016/02/26/Android5-0%E4%B8%ADWebView%E9%BB%98%E8%AE%A4%E4%B8%8D%E5%8A%A0%E8%BD%BDMixContent%E7%9A%84%E8%A7%A3%E5%86%B3%E6%96%B9%E6%A1%88/
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    webSetting.setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
                }

            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
        removeJavascriptInterface("searchBoxJavaBridge_");
        removeJavascriptInterface("accessibility");
        removeJavascriptInterface("accessibilityTraversal");
        setInitialScale(100);
        setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        setHorizontalScrollBarEnabled(false);
        setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    requestFocus();
                }
                if (mWebViewListener != null) {
                    mWebViewListener.onProgressChanged(newProgress);
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                if (mWebViewListener != null) {
                    mWebViewListener.onReceivedTitle(title);
                }
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message,
                                     final JsResult result) {
                AlertDialog.Builder b2 = new AlertDialog.Builder(
                        getContext())
                        .setTitle("提示")
                        .setMessage(message)
                        .setPositiveButton("ok",
                                new AlertDialog.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        result.confirm();
                                    }
                                });

                b2.setCancelable(false);
                b2.create();
                b2.show();
                return true;
            }
        });

        setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if (mWebViewListener != null) {
                    mWebViewListener.onPageStarted(url);
                }
                startTimeoutTimer();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (mWebViewListener != null) {
                    mWebViewListener.onPageFinished(url);
                }
                stopTimeoutTimer();
            }

            @Override
            @RequiresApi(24)
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                LogUtil.d(TAG, "shouldOverrideUrlLoading req url:" + request.getUrl());
                if (null != request.getUrl()) {
                    return this.shouldOverrideUrlLoading(view, request.getUrl().toString());
                } else {
                    return super.shouldOverrideUrlLoading(view, request);
                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                LogUtil.i(TAG, "shouldOverrideUrlLoading url:" + url);
//                if (!UriUtil.isNetworkUri(Uri.parse(url))) {
//                    return false;
//                }

                loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                LogUtil.d(TAG, "onReceivedSslError: " + error.getPrimaryError());
                handler.proceed();
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                String info = String.format("onReceivedError code:%d desc:%s url:%s", errorCode, description, failingUrl);
                LogUtil.e(TAG, info);
                loadUrl("file:///android_asset/web_error.html");
                requestFocus();
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
                LogUtil.d(TAG, "onLoadResource");
                if (view.getProgress() == 100) {
                    if (mWebViewListener != null) {
                        mWebViewListener.onPageFinished(url);
                    }
                }
            }
        });

        addJavascriptInterface(new JavaScriptInterfaceImpl(getContext(), jsInterface), "nativeApp");
    }

    @Override
    public void loadUrl(String url) {
        LogUtil.d(TAG, "loadUrl:"+ url);
        super.loadUrl(url);
    }

    @Override
    public void destroy() {
        LogUtil.d(TAG, "destroy");
        stopTimeoutTimer();
        if (null != mHandler) {
            mHandler.removeCallbacksAndMessages(null);
        }

        // in android 5.1(sdk:21) we should invoke this to avoid memory leak
        // see (https://coolpers.github.io/webview/memory/leak/2015/07/16/
        // android-5.1-webview-memory-leak.html)
        ViewParent parent = getParent();
        if (parent != null) {
            ((ViewGroup) parent).removeView(this);
        }
        removeAllViews();
        stopLoading();
        if (null != getSettings()) {
            getSettings().setJavaScriptEnabled(false);
        }
        clearHistory();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            unInitJavaScriptSurport();
        }

        try {
            super.destroy();
        } catch (Throwable e) {
            LogUtil.e(TAG, "destroy exception", e);
        }
    }

    @TargetApi(17)
    private  void unInitJavaScriptSurport(){
        removeJavascriptInterface("nativeApp");
    }

    public interface WebViewListener {
        void onPageStarted(String url);

        void onPageFinished(String url);

        void onProgressChanged(int newProgress);

        void onReceivedTitle(String text);

        void onPageTimeout();
    }

    public static class WebViewListenerAdapter implements WebViewListener {

        @Override
        public void onPageStarted(String url) {

        }

        @Override
        public void onPageFinished(String url) {

        }

        @Override
        public void onProgressChanged(int newProgress) {

        }

        @Override
        public void onReceivedTitle(String text) {

        }

        @Override
        public void onPageTimeout() {

        }
    }

    public void setWebViewListener(WebViewListener listener) {
        mWebViewListener = listener;
    }

    private Handler mHandler;
    public static long TIME_OUT = 60000;

    public void startTimeoutTimer() {
        if (null == mHandler) {
            mHandler = new Handler();
        }

        mHandler.removeCallbacksAndMessages(null);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //这里对已经显示出页面且加载超时的情况不做处理
                if (InternalWebView.this.getProgress() < 100) {
                    LogUtil.d(TAG, "-------加载超时------->");
//                    NorToast.error("加载超时");
                    if (mWebViewListener != null) {
                        mWebViewListener.onPageTimeout();
                    }
                }
            }
        }, TIME_OUT);
    }

    public void stopTimeoutTimer() {
        try {
            if (null != mHandler) {
                mHandler.removeCallbacksAndMessages(null);
                mHandler = null;
            }

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Keep
    public static class JavaScriptInterfaceImpl {

        Context mContext;

        @Nullable JsInterface mJsInterface;

        // Instantiate the interface and set the context
        public JavaScriptInterfaceImpl(Context c) {
            mContext = c;
        }

        public JavaScriptInterfaceImpl(Context context, @Nullable JsInterface jsInterface) {
            mContext = context;
            mJsInterface = jsInterface;
        }

        /**
         *
         * @param jsonParam request:
            {
                "name": "xxx",
                "params": "xxxx",
                "callback": "xxxx"   // 前端设置的回调函数名
            }
         */
        @JavascriptInterface
        public void invoke(String jsonParam) {
            if (null != mJsInterface) {
                JSONObject jsonObject = JsonToObject.toObject(jsonParam);
                if (null != jsonObject) {
                    String name = jsonObject.getString("name");
                    mJsInterface.invoke(name, jsonObject);
                } else {
                    LogUtil.e(TAG, "parse js request failed");
                }
            } else {
                LogUtil.e(TAG, "no jsInterface register");
            }
        }

    }
}
