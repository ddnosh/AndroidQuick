package la.xiong.androidquick.demo.base.databinding;

import android.databinding.ViewDataBinding;

import la.xiong.androidquick.demo.base.BaseFragment;

/**
 * 集成databinding
 *
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public abstract class BaseTFragment<T extends ViewDataBinding> extends BaseFragment {

    protected String TAG = "BaseTFragment";

    public T getBinding() {
        return (T) binding;
    }

}
