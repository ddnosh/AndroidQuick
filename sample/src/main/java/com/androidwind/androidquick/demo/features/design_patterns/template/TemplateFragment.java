package com.androidwind.androidquick.demo.features.design_patterns.template;

import android.os.Bundle;
import android.view.View;

import com.androidwind.androidquick.demo.R;
import com.androidwind.androidquick.demo.base.BaseFragment;
import com.androidwind.annotation.annotation.BindTag;
import com.androidwind.annotation.annotation.TagInfo;

import butterknife.OnClick;


/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@BindTag(type = TagInfo.Type.FRAGMENT, tags = {"template", "模板"}, description = "模板设计模式")
public class TemplateFragment extends BaseFragment {
    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_design_pattern_template;
    }

    @OnClick({R.id.btn_template})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_template:
                Car bmw = new BMW();
                bmw.move();
                Car benz = new Benz();
                benz.move();
                break;
        }
    }
}
