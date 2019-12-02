package com.androidwind.androidquick.demo.injector.component;

import android.content.Context;

import com.androidwind.androidquick.demo.MyApplication;
import com.androidwind.androidquick.demo.injector.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

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

    // RetrofitManager getRetrofitManager();  //提供http的帮助类

    void inject(MyApplication application);
}
