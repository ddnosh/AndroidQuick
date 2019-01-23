package la.xiong.androidquick.demo.base;

import android.databinding.ViewDataBinding;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public abstract class BaseVFragment<V extends ViewDataBinding> extends BaseFragment {

    protected String TAG = "BaseVFragment";

    public V getBinding() {
        return (V) binding;
    }

}
