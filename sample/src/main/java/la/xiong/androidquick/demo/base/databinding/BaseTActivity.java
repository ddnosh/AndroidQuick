package la.xiong.androidquick.demo.base.databinding;

import android.databinding.ViewDataBinding;

import la.xiong.androidquick.demo.base.BaseActivity;

/**
 * 集成databinding
 *
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public abstract class BaseTActivity<T extends ViewDataBinding> extends BaseActivity {

    protected String TAG = "BaseTActivity";

    public T getBinding() {
        return (T) binding;
    }

}
