package la.xiong.androidquick.demo.features.design_patterns.builder;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseFragment;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class BuilderFragment extends BaseFragment {

    @BindView(R.id.tv_output1)
    TextView mTextView1;
    @BindView(R.id.tv_output2)
    TextView mTextView2;

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        Student student1 = new Student.StudentBuilder().setName("张三").setAge(18).setSex(true).build();
        mTextView1.setText(student1.toString());
        Student student2 = Student.newInstance().setName("李四").setAge(20).setSex(false).build();
        mTextView2.setText(student2.toString());
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_design_pattern_builder;
    }
}
