package com.androidwind.androidquick.demo.features.architecture.segment.type2.module3;

import android.view.View;
import android.widget.TextView;

import com.androidwind.androidquick.demo.R;
import com.androidwind.androidquick.demo.features.architecture.segment.type2.mvp.BaseTModule;
import com.androidwind.androidquick.util.ToastUtil;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */

public class Module3 extends BaseTModule<Module3Presenter> implements Module3Contract.View {
    private TextView mTextView;

    public Module3(View rootView) {
        super(rootView);
    }

    @Override
    protected void initView() {
        super.initView();
        mTextView =(TextView) findViewById(R.id.tv_module3);
        mTextView.setText("Module3 loaded!");
    }

    @Override
    protected void release() {
        super.release();
        System.out.println("Module3 released!");
    }

    @Override
    public void toastSomething() {
        ToastUtil.showToast("here is a module with MVP");
    }
}
