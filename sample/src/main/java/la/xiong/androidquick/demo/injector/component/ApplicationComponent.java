package la.xiong.androidquick.demo.injector.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import la.xiong.androidquick.demo.MyApplication;
import la.xiong.androidquick.demo.injector.module.ApplicationModule;
import la.xiong.androidquick.network.retrofit.RetrofitManager;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@Singleton
@Component(
        modules = {
                ApplicationModule.class
        }
)
public interface ApplicationComponent {
    Context getContext();  // 提供App的Context

    RetrofitManager getRetrofitManager();  //提供http的帮助类

    void inject(MyApplication application);
}
