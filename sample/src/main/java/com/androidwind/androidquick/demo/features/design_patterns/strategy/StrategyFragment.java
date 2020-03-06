package com.androidwind.androidquick.demo.features.design_patterns.strategy;

import com.androidwind.androidquick.demo.features.CodeAndRunFragment;
import com.androidwind.annotation.annotation.BindTag;
import com.androidwind.annotation.annotation.TagInfo;


/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@BindTag(type = TagInfo.Type.FRAGMENT, tags = {"strategy", "策略"}, description = "策略模式 + 切换Log引擎实例")
public class StrategyFragment extends CodeAndRunFragment {

    @Override
    public String getMarkDownUrl() {
        return "StrategyFragment";
    }

    @Override
    public String[] getItems() {
        return new String[]{"TinyLog", "System default log"};
    }

    @Override
    public void clickItem(int position) {
        switch (position) {
            case 0:
                //tiny log
                LogLoader.load(new TinyLogProcessor());
                LogLoader.getInstance().d("this is tiny log");
                break;
            case 1:
                //system default log
                LogLoader.load(new DefaultLogProcessor());
                LogLoader.getInstance().d("this is system default log");
                // LogLoader2.getInstance().useDmode("this is system default log");
                break;
        }
    }
}
