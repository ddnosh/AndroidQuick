package com.androidwind.androidquick.demo;

import android.app.Activity;

import com.androidwind.log.TinyLog;
import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;

import com.androidwind.androidquick.demo.crash.CrashHandler;
import com.androidwind.androidquick.demo.features.module.db.greendao.DBManager;
import com.androidwind.androidquick.demo.features.search.SearchManager;
import com.androidwind.androidquick.demo.injector.component.ApplicationComponent;
import com.androidwind.androidquick.demo.injector.component.DaggerApplicationComponent;
import com.androidwind.androidquick.demo.injector.module.ApplicationModule;
import com.androidwind.androidquick.demo.ui.AQActivityLifecycleCallbacks;
import com.androidwind.androidquick.util.LogUtil;
import com.androidwind.androidquick.util.SpUtil;
import com.androidwind.androidquick.util.ToastUtil;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class MyApplication extends android.support.multidex.MultiDexApplication {

    private static MyApplication INSTANCE;

    private AQActivityLifecycleCallbacks lifecycleCallback;

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        //get application
        if (INSTANCE == null) {
            INSTANCE = this;
        }
        //log switcher
        LogUtil.setLogOn(true);
        //lifecyclecallback
        lifecycleCallback = new AQActivityLifecycleCallbacks();
        registerActivityLifecycleCallbacks(lifecycleCallback);
        //init ToastUtil
        ToastUtil.register(this);
        //init SpUtil
        SpUtil.init(this);
        //init greendao
        DBManager.getInstance().init(getApplicationContext());
        //init immersion

        //init stetho
        Stetho.initializeWithDefaults(this);
        //init crashhandler
        CrashHandler.getInstance().init(this);
        //init tinylog
        TinyLog.config().setEnable(true).apply();
        //init tag
        SearchManager.getInstance().init();
    }

    public static synchronized MyApplication getInstance() {
        return INSTANCE;
    }

    public static Activity getCurrentVisibleActivity() {
        return INSTANCE.lifecycleCallback.getCurrentVisibleActivity();
    }

    public static boolean isInForeground() {
        return null != INSTANCE && null != INSTANCE.lifecycleCallback && INSTANCE.lifecycleCallback.isInForeground();
    }

    //dagger2:get ApplicationComponent
    public static ApplicationComponent getApplicationComponent() {
        return DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(INSTANCE)).build();
    }

}
