package la.xiong.androidquick.demo.base.mvp_databinding;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

import javax.inject.Inject;

import la.xiong.androidquick.demo.base.BaseFragment;
import la.xiong.androidquick.ui.mvp.BasePresenter;
import la.xiong.androidquick.demo.MyApplication;
import la.xiong.androidquick.demo.injector.component.DaggerFragmentComponent;
import la.xiong.androidquick.demo.injector.component.FragmentComponent;
import la.xiong.androidquick.demo.injector.module.FragmentModule;

/**
 * mvp:集成dagger2 + databinding
 *
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public abstract class BaseTVFragment<T extends BasePresenter, V extends ViewDataBinding> extends BaseFragment {

    @Inject
    public T mPresenter;
    protected Context mContext;
    protected String TAG = "BaseTVFragment";
    public V getBinding(){
        return (V) binding;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

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
