package la.xiong.androidquick.demo.injector.component;

import dagger.Component;
import la.xiong.androidquick.demo.injector.FragmentScope;
import la.xiong.androidquick.demo.injector.module.FragmentModule;
import la.xiong.androidquick.demo.architecture.mvp.fragment_dagger.MVPDaggerFragment;
import la.xiong.androidquick.demo.module.network.retrofit.network1.Network1Fragment;
import la.xiong.androidquick.demo.other.rxlifecycle.RxLifecycleFragment;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@FragmentScope
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(Network1Fragment network1Fragment);
    void inject(MVPDaggerFragment MVPDaggerFragment);
    void inject(RxLifecycleFragment rxLifecycleFragment);
}
