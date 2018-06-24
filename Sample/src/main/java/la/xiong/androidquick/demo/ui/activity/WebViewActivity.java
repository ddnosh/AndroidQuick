package la.xiong.androidquick.demo.ui.activity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import butterknife.BindView;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseActivity;
import la.xiong.androidquick.tool.StringUtil;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class WebViewActivity extends BaseActivity {

    @BindView(R.id.wvWebView)
    WebView wvWebView;
    @BindView(R.id.pbWebView)
    ProgressBar pbWebView;

    private Bundle mBundle;

    public static final String URL = "https://github.com/ddnosh";

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_webview;
    }

    @Override
    protected void initViewsAndEvents() {
        initData();
    }

    @SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface"})
    public void initData() {
        String url = mBundle.getString("url");
        if (StringUtil.isEmpty(url)) {
            url = URL;
        }

        WebSettings webSettings = wvWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        wvWebView.requestFocus();

        // 设置setWebChromeClient对象
        wvWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                getToolbar().setTitle(title);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                pbWebView.setProgress(newProgress);
            }
        });

        wvWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                wvWebView.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                getToolbar().setTitle(StringUtil.getTrimedString(wvWebView.getUrl()));
                pbWebView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                getToolbar().setTitle(StringUtil.getTrimedString(wvWebView.getTitle()));
                pbWebView.setVisibility(View.GONE);
            }
        });

        wvWebView.loadUrl(url);
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        mBundle = extras;
    }

    @Override
    protected boolean isLoadDefaultTitleBar() {
        return true;
    }

    //系统自带监听方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Override
    public void onBackPressed() {
        if (wvWebView.canGoBack()) {
            wvWebView.goBack();
            return;
        }

        super.onBackPressed();
    }

    //类相关监听<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Override
    protected void onPause() {
        super.onPause();
        wvWebView.onPause();
    }

    @Override
    protected void onResume() {
        wvWebView.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        if (wvWebView != null) {
            wvWebView.destroy();
            wvWebView = null;
        }
        super.onDestroy();
    }
}
