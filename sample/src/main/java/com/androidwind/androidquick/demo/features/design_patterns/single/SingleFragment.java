package com.androidwind.androidquick.demo.features.design_patterns.single;

import com.androidwind.androidquick.demo.features.CodeAndRunFragment;
import com.androidwind.annotation.annotation.BindTag;
import com.androidwind.annotation.annotation.TagInfo;


/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@BindTag(type = TagInfo.Type.FRAGMENT, tags = {"single", "单例"}, description = "单例 + 静态")
public class SingleFragment extends CodeAndRunFragment {

    @Override
    public String getMarkDownUrl() {
        return "SingleFragment";
    }

    @Override
    public String[] getItems() {
        return new String[]{"单例", "静态"};
    }

    @Override
    public void clickItem(int position) {
        switch (position) {
            case 0:
                SingletonDemo.getInstance().printSomething();
                SingletonEnumDemo.INSTANCE.printSomething();
                break;
            case 1:
                StaticDemo.printSomething();
                break;
        }
    }
}
