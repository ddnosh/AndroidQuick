package la.xiong.androidquick.demo.base.mvp;

import la.xiong.androidquick.demo.base.BaseFragment;
import la.xiong.androidquick.demo.tool.TUtil;
import la.xiong.androidquick.ui.mvp.BasePresenter;

/**
 * mvp
 *
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public abstract class BaseTFragment<T extends BasePresenter> extends BaseFragment {

    public T mPresenter;
    protected String TAG = "BaseTFragment";

    @Override
    protected void initCreate() {
        mPresenter = TUtil.getInstance(this, 0);

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
}
