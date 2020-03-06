package com.androidwind.androidquick.demo.features.design_patterns.builder;

import android.os.Bundle;
import android.widget.TextView;

import com.androidwind.androidquick.demo.R;
import com.androidwind.androidquick.demo.base.BaseFragment;
import com.androidwind.androidquick.demo.features.CodeAndRunFragment;
import com.androidwind.annotation.annotation.BindTag;
import com.androidwind.annotation.annotation.TagInfo;

import butterknife.BindView;


/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@BindTag(type = TagInfo.Type.FRAGMENT, tags = {"builder", "建造", "构建"}, description = "Builder模式实例")
public class BuilderFragment extends CodeAndRunFragment {
    @Override
    public String getMarkDownUrl() {
        return "BuilderFragment";
    }

    @Override
    public String[] getItems() {
        return new String[]{"Builder实现一", "Builder实现二", "Builder实现三"};
    }

    @Override
    public void clickItem(int position) {
        switch (position) {
            case 0:
                Student student1 = new Student.StudentBuilder().setName("张三").setAge(18).setSex(true).build();
                showOutput(student1.toString());
                break;
            case 1:
                Student student2 = Student.newInstance().setName("李四").setAge(20).setSex(false).build();
                showOutput(student2.toString());
                break;
            case 2:
                Student student3 = new Student().setAge(22).setName("王五").setSex(true);
                showOutput(student3.toString());
                break;
        }
    }
}
