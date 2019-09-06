package com.androidwind.androidquick.demo.features.design_patterns.strategy;

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
@BindTag(type = TagInfo.Type.FRAGMENT, tags = {"strategy", "策略"}, description = "策略模式 + 切换Log引擎实例")
public class StrategyFragment extends BaseFragment {
    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_solution_switcher;
    }

    @OnClick({R.id.btn_log1, R.id.btn_log2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_log1:
                LogLoader.load(new TinyLogProcessor());
                LogLoader.getInstance().d("this is tiny log");
                break;
            case R.id.btn_log2:
                LogLoader.load(new DefaultLogProcessor());
                LogLoader.getInstance().d("this is system default log");
                // LogLoader2.getInstance().useDmode("this is system default log");
                break;
        }
    }
}
