package com.androidwind.androidquick.demo.features.architecture.segment.type1;

import com.androidwind.androidquick.util.ToastUtil;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */

public class Page1 extends PageController {
    public Page1(BasePageFragment fragment) {
        super(fragment);
    }

    @Override
    protected void initView() {

    }

    @Override
    public void enter() {
        super.enter();
        ToastUtil.showToast("Page1 entered!");
    }

    @Override
    public void out() {
        super.out();
        ToastUtil.showToast("Page1 outed!");
    }

    @Override
    public void release() {

    }
}
