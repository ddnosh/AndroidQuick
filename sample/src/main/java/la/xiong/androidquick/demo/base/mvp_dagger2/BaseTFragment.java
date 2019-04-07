package la.xiong.androidquick.demo.base.mvp_dagger2;

import javax.inject.Inject;

import la.xiong.androidquick.demo.MyApplication;
import la.xiong.androidquick.demo.base.BaseFragment;
import la.xiong.androidquick.demo.injector.component.DaggerFragmentComponent;
import la.xiong.androidquick.demo.injector.component.FragmentComponent;
import la.xiong.androidquick.demo.injector.module.FragmentModule;
import la.xiong.androidquick.ui.mvp.BasePresenter;

/**
 * mvp:集成dagger2
 *
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public abstract class BaseTFragment<T extends BasePresenter> extends BaseFragment {
    @Inject
    public T mPresenter;
    protected String TAG = "BaseTFragment";

    @Override
    protected void initCreate() {
        //dagger2
        initInjector();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void initDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
    }

    protected void initInjector() {

    }

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .applicationComponent(MyApplication.getApplicationComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    protected FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

}
