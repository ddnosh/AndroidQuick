package com.androidwind.androidquick.demo.features.design_patterns.strategy;

import com.androidwind.log.TinyLog;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class TinyLogProcessor implements ILogProcessor {
    @Override
    public void v(String vLog) {
        TinyLog.v("tinylog:" + vLog);
    }

    @Override
    public void d(String dLog) {
        TinyLog.d("tinylog:" + dLog);
    }

    @Override
    public void i(String iLog) {
        TinyLog.i("tinylog:" + iLog);
    }

    @Override
    public void e(String eLog) {
        TinyLog.e("tinylog:" + eLog);
    }
}
