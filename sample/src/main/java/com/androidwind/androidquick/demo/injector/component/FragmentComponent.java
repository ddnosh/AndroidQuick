package com.androidwind.androidquick.demo.injector.component;

import dagger.Component;
import com.androidwind.androidquick.demo.injector.FragmentScope;
import com.androidwind.androidquick.demo.injector.module.FragmentModule;
import com.androidwind.androidquick.demo.features.architecture.mvp.fragment_dagger.MVPDaggerFragment;
import com.androidwind.androidquick.demo.features.module.network.retrofit.network1.Network1Fragment;
import com.androidwind.androidquick.demo.features.other.rxlifecycle.RxJavaLifecycleFragment;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@FragmentScope
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(Network1Fragment network1Fragment);
    void inject(MVPDaggerFragment MVPDaggerFragment);
    void inject(RxJavaLifecycleFragment rxJavaLifecycleFragment);
}
