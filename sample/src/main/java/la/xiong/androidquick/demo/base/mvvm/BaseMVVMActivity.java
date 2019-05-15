package la.xiong.androidquick.demo.base.mvvm;

import la.xiong.androidquick.demo.base.BaseActivity;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public abstract class BaseMVVMActivity<T extends BaseViewModel> extends BaseActivity {

    protected T viewModel;

    @Override
    protected void initCreate() {
        if (viewModel == null) {
            viewModel = getViewModel();
        }
    }

    protected abstract T getViewModel();
}
