package la.xiong.androidquick.demo;

import android.app.Application;

import la.xiong.androidquick.demo.db.DBManager;
import la.xiong.androidquick.demo.injector.component.ApplicationComponent;
import la.xiong.androidquick.demo.injector.component.DaggerApplicationComponent;
import la.xiong.androidquick.demo.injector.module.ApplicationModule;
import la.xiong.androidquick.network.RetrofitManager;
import la.xiong.androidquick.tool.SpUtil;
import la.xiong.androidquick.tool.ToastUtil;
import spa.lyh.cn.statusbarlightmode.ImmersionConfiguration;
import spa.lyh.cn.statusbarlightmode.ImmersionMode;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class MyApplication extends Application {

    private static MyApplication INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        //获取全局变量Application
        if (INSTANCE == null) {
            INSTANCE = this;
        }
        //初始化ToastUtil
        ToastUtil.register(this);
        //初始化SpUtil
        SpUtil.init(this);
        //初始化greendao
        DBManager.getInstance().init(getApplicationContext());
        //初始化url
        RetrofitManager.initBaseUrl("http://gank.io/api/");
        //沉浸式标题栏
        ImmersionConfiguration configuration = new ImmersionConfiguration.Builder(this)
                .enableImmersionMode(ImmersionConfiguration.ENABLE)
                .setColor(R.color.base_bg)//默认标题栏颜色
                .build();
        ImmersionMode.getInstance().init(configuration);
    }

    public static synchronized MyApplication getInstance() {
        return INSTANCE;
    }

    //dagger2:get ApplicationComponent
    public static ApplicationComponent getApplicationComponent() {
        return DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(INSTANCE)).build();
    }

}
