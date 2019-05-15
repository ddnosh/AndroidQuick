package la.xiong.androidquick.demo.base.mvvm;

import la.xiong.androidquick.demo.base.BaseFragment;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public abstract class BaseMVVMFragment<T extends BaseViewModel> extends BaseFragment {

    protected T viewModel;

    @Override
    protected void initCreate() {
        if (viewModel == null) {
            viewModel = getViewModel();
        }
    }

    protected abstract T getViewModel();
}
