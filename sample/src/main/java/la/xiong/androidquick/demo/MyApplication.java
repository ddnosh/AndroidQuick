package la.xiong.androidquick.demo;

import android.app.Activity;

import com.alibaba.fastjson.JSONObject;
import com.androidwind.annotation.TagService;
import com.androidwind.log.TinyLog;
import com.androidwind.task.AdvancedTask;
import com.androidwind.task.TinyTaskExecutor;
import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;

import java.util.List;
import java.util.Map;

import la.xiong.androidquick.demo.crash.CrashHandler;
import la.xiong.androidquick.demo.features.module.db.greendao.DBManager;
import la.xiong.androidquick.demo.features.module.db.ormlite.Tag;
import la.xiong.androidquick.demo.features.module.db.ormlite.TagDao;
import la.xiong.androidquick.demo.injector.component.ApplicationComponent;
import la.xiong.androidquick.demo.injector.component.DaggerApplicationComponent;
import la.xiong.androidquick.demo.injector.module.ApplicationModule;
import la.xiong.androidquick.demo.ui.AQActivityLifecycleCallbacks;
import la.xiong.androidquick.module.network.retrofit.RetrofitManager;
import la.xiong.androidquick.tool.LogUtil;
import la.xiong.androidquick.tool.SpUtil;
import la.xiong.androidquick.tool.StringUtil;
import la.xiong.androidquick.tool.ToastUtil;
import spa.lyh.cn.statusbarlightmode.ImmersionConfiguration;
import spa.lyh.cn.statusbarlightmode.ImmersionMode;

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
        ImmersionConfiguration configuration = new ImmersionConfiguration.Builder(this)
                .enableImmersionMode(ImmersionConfiguration.ENABLE)
                .setColor(R.color.base_bg)//default color
                .build();
        ImmersionMode.getInstance().init(configuration);
        //init stetho
        Stetho.initializeWithDefaults(this);
        //init crashhandler
        CrashHandler.getInstance().init(this);
        //init tinylog
        TinyLog.config().setEnable(true).apply();
        //init tag
        updateTag();
    }

    private void updateTag() {
        TinyTaskExecutor.execute(new AdvancedTask<Void>() {
            @Override
            public Void doInBackground() {
                try {
                    Class<?> aClass = Class.forName("com.androidwind.annotation.TagService");
                    TagService tagService = (TagService) aClass.newInstance();
                    // TinyLog.d(Arrays.toString(tagService.map.entrySet().toArray()));
                    JSONObject jsonObject = new JSONObject();
                    for(Object entry: tagService.getMap().entrySet()) {
                        if (entry instanceof Map.Entry) {
                            Map.Entry mEntry = (Map.Entry)entry;
                            String value = (String)mEntry.getValue();
                            String sTemp = value.substring(1, value.length()-1);
                            String[] sArray = sTemp.split(", ");
                            int count = 0;
                            for (String s : sArray) {
                                count++;
                                if (!StringUtil.isEmpty(s)) {
                                    jsonObject.put("tag" + count, s);
                                }
                            }
                            String key = (String)mEntry.getKey();
                            TagDao.getInstance().createOrUpdate(key, jsonObject.toJSONString());
                        }
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            public void onSuccess(Void s) {
                ToastUtil.showToast("Tag updated!");
                List<Tag> tagList = TagDao.getInstance().getAllTag();
                LogUtil.i("MyApplication", tagList.toString());
            }

            @Override
            public void onFail(Throwable throwable) {

            }
        });
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
