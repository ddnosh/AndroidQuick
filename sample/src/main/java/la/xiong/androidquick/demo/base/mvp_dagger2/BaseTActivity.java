package la.xiong.androidquick.demo.base.mvp_dagger2;

import javax.inject.Inject;

import la.xiong.androidquick.demo.MyApplication;
import la.xiong.androidquick.demo.base.BaseActivity;
import la.xiong.androidquick.demo.injector.component.ActivityComponent;
import la.xiong.androidquick.demo.injector.component.DaggerActivityComponent;
import la.xiong.androidquick.demo.injector.module.ActivityModule;
import la.xiong.androidquick.tool.LogUtil;
import la.xiong.androidquick.ui.mvp.BasePresenter;

/**
 * mvp:集成dagger2
 *
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public abstract class BaseTActivity<T extends BasePresenter> extends BaseActivity {

    protected static String TAG = "BaseTActivity";

    @Inject
    public T mPresenter;

    @Override
    protected void initCreate() {
        LogUtil.i(TAG, "onCreate:" + getClass().getSimpleName());
        //dagger2
        initInjector();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void initDestroy() {
        LogUtil.i(TAG, "onDestroy:" + getClass().getSimpleName());
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
    }

    protected void initInjector() {

    }

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .applicationComponent(MyApplication.getInstance().getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

}
