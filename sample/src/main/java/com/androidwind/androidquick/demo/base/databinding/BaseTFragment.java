package com.androidwind.androidquick.demo.base.databinding;

import com.androidwind.androidquick.demo.base.BaseFragment;

import androidx.databinding.ViewDataBinding;

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
