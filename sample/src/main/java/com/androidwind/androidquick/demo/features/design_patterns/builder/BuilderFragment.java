package com.androidwind.androidquick.demo.features.design_patterns.builder;

import android.os.Bundle;
import android.widget.TextView;

import com.androidwind.androidquick.demo.R;
import com.androidwind.androidquick.demo.base.BaseFragment;
import com.androidwind.annotation.annotation.BindTag;
import com.androidwind.annotation.annotation.TagInfo;

import butterknife.BindView;


/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@BindTag(type = TagInfo.Type.FRAGMENT, tags = {"builder", "建造", "构建"}, description = "Builder模式实例")
public class BuilderFragment extends BaseFragment {

    @BindView(R.id.tv_output1)
    TextView mTextView1;
    @BindView(R.id.tv_output2)
    TextView mTextView2;
    @BindView(R.id.tv_output3)
    TextView mTextView3;

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        Student student1 = new Student.StudentBuilder().setName("张三").setAge(18).setSex(true).build();
        mTextView1.setText(student1.toString());
        Student student2 = Student.newInstance().setName("李四").setAge(20).setSex(false).build();
        mTextView2.setText(student2.toString());
        Student student3 = new Student().setAge(22).setName("王五").setSex(true);
        mTextView3.setText(student3.toString());
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_design_pattern_builder;
    }
}
