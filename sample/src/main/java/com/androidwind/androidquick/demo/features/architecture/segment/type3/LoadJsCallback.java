package com.androidwind.androidquick.demo.features.architecture.segment.type3;

import android.content.Context;
import android.webkit.WebView;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public interface LoadJsCallback {

    void loadJavaScript(String js);

    Context getContext();

    WebView getCurrentWebView();
}
