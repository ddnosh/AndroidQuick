package la.xiong.androidquick.demo.base.mvp_dagger2;

import android.os.Bundle;

import javax.inject.Inject;

import la.xiong.androidquick.demo.base.BaseFragment;
import la.xiong.androidquick.ui.mvp.BasePresenter;
import la.xiong.androidquick.demo.MyApplication;
import la.xiong.androidquick.demo.injector.component.DaggerFragmentComponent;
import la.xiong.androidquick.demo.injector.component.FragmentComponent;
import la.xiong.androidquick.demo.injector.module.FragmentModule;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //dagger2
        initInjector();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        super.onDestroy();
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
