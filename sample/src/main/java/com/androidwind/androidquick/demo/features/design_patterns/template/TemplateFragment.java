package com.androidwind.androidquick.demo.features.design_patterns.template;

import android.os.Bundle;
import android.view.View;

import com.androidwind.androidquick.demo.R;
import com.androidwind.androidquick.demo.base.BaseFragment;
import com.androidwind.androidquick.demo.features.CodeAndRunFragment;
import com.androidwind.annotation.annotation.BindTag;
import com.androidwind.annotation.annotation.TagInfo;

import butterknife.OnClick;


/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@BindTag(type = TagInfo.Type.FRAGMENT, tags = {"template", "模板"}, description = "模板设计模式")
public class TemplateFragment extends CodeAndRunFragment {

    @Override
    public String getMarkDownUrl() {
        return "TemplateFragment";
    }

    @Override
    public String[] getItems() {
        return new String[]{"模板"};
    }

    @Override
    public void clickItem(int position) {
        switch (position) {
            case 0:
                Car bmw = new BMW();
                bmw.move();
                Car benz = new Benz();
                benz.move();
                break;
        }
    }
}
