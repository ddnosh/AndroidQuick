package com.androidwind.androidquick.demo.injector.component;

import android.app.Activity;
import android.content.Context;

import dagger.Component;
import com.androidwind.androidquick.demo.features.architecture.mvp.activity_dagger.MVPDaggerActivity;
import com.androidwind.androidquick.demo.injector.ActivityScope;
import com.androidwind.androidquick.demo.injector.module.ActivityModule;
import com.androidwind.androidquick.demo.features.module.network.retrofit.NetworkActivity;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class})
public interface ActivityComponent {

    Activity getActivity();
    Context getContext();

    void inject(NetworkActivity networkActivity);
    void inject(MVPDaggerActivity mvpActivity);
}
