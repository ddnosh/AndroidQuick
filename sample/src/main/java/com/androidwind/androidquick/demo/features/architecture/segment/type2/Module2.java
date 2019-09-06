package com.androidwind.androidquick.demo.features.architecture.segment.type2;

import android.view.View;
import android.widget.TextView;

import com.androidwind.androidquick.demo.R;


/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */

public class Module2 extends BaseModule{
    private IModuleCallback moduleCallback;
    private TextView mTextView;

    public Module2(View rootView, IModuleCallback callback) {
        super(rootView);
        moduleCallback = callback;
    }

    public void modify(String content) {
        mTextView.setText(content);
        content = getModule(Module1.class).getContent();
        moduleCallback.doModify(content);
    }

    @Override
    protected void initView() {
        mTextView =(TextView) findViewById(R.id.tv_module2);
        mTextView.setText("Module2 loaded!");
    }

    @Override
    protected void release() {
        System.out.println("Module2 released!");
    }
}
