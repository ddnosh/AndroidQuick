package la.xiong.androidquick.demo.injector.component;

import android.app.Activity;

import dagger.Component;
import la.xiong.androidquick.demo.injector.FragmentScope;
import la.xiong.androidquick.demo.injector.module.FragmentModule;
import la.xiong.androidquick.demo.module.mvp.fragment.MvpFragment;
import la.xiong.androidquick.demo.module.network.retrofit.network1.Network1Fragment;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@FragmentScope
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(Network1Fragment network1Fragment);
    void inject(MvpFragment mvpFragment);
}
