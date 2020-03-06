package com.androidwind.androidquick.demo.features.design_patterns.responsibilitychain;

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
@BindTag(type = TagInfo.Type.FRAGMENT, tags = {"responsibility", "责任链"}, description = "责任链模式实例")
public class ResponsibilityChainFragment extends CodeAndRunFragment {

    @Override
    public String getMarkDownUrl() {
        return "ResponsibilityChainFragment";
    }

    @Override
    public String[] getItems() {
        return new String[]{"责任链"};
    }

    @Override
    public void clickItem(int position) {
        switch (position) {
            case 0:
                BaseFilter shape = new ShapeFilter();
                BaseFilter weight = new WeightFilter();
                BaseFilter date = new DateFilter();
                shape.setFilter(weight);
                weight.setFilter(date);
                shape.doFilter("circle5kg2019");
                break;
        }
    }
}
