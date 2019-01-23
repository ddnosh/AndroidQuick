package la.xiong.androidquick.demo.base;

import android.databinding.ViewDataBinding;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public abstract class BaseVActivity<V extends ViewDataBinding> extends BaseActivity {

    protected String TAG = "BaseVActivity";

    public V getBinding() {
        return (V) binding;
    }

}
