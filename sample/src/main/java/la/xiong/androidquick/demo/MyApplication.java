package la.xiong.androidquick.demo;

import android.app.Activity;

import com.androidwind.log.TinyLog;
import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;

import la.xiong.androidquick.demo.crash.CrashHandler;
import la.xiong.androidquick.demo.features.module.db.greendao.DBManager;
import la.xiong.androidquick.demo.features.search.SearchManager;
import la.xiong.androidquick.demo.injector.component.ApplicationComponent;
import la.xiong.androidquick.demo.injector.component.DaggerApplicationComponent;
import la.xiong.androidquick.demo.injector.module.ApplicationModule;
import la.xiong.androidquick.demo.ui.AQActivityLifecycleCallbacks;
import la.xiong.androidquick.module.network.retrofit.RetrofitManager;
import la.xiong.androidquick.tool.LogUtil;
import la.xiong.androidquick.tool.SpUtil;
import la.xiong.androidquick.tool.ToastUtil;

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
        //init retrofit url
        RetrofitManager.initBaseUrl("http://gank.io/api/");
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
