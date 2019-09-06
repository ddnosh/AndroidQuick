package com.androidwind.androidquick.demo.features.module.ioc.dagger2;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */

import dagger.Component;
import com.androidwind.androidquick.demo.injector.FragmentScope;

@FragmentScope
@Component(modules = TestModule.class)
public interface Test2Component {

    void inject(Dagger2Fragment dagger2Fragment);
}
