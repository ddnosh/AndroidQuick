package com.androidwind.androidquick.demo.features.architecture.mvvm.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.androidwind.androidquick.demo.R;
import com.androidwind.androidquick.demo.base.BaseActivity;
import com.androidwind.androidquick.demo.bean.NameBean;
import com.androidwind.androidquick.demo.features.architecture.mvvm.fragment.TomFragment;
import com.androidwind.annotation.annotation.BindTag;
import com.androidwind.annotation.annotation.TagInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@BindTag(type = TagInfo.Type.ACTIVITY, tags = {"MVVM"}, description = "Activity + MVVM实例")
public class MVVMActivity extends BaseActivity {

    @BindView(R.id.tv_activity_mvvm1)
    TextView mTextView1;
    @BindView(R.id.tv_activity_mvvm2)
    TextView mTextView2;

    private MVVMViewModel1 viewModel1;

    private MVVMViewModel2 viewModel2;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_architecture_mvvm_activity;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        viewModel1 = ViewModelProviders.of(this, new MVVMFactory1(new MVVMRepository1(), this)).get(MVVMViewModel1.class);

        viewModel1.getData().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> s) {
                mTextView1.setText("size is " + s.size());
            }
        });

        viewModel2 = ViewModelProviders.of(this, new MVVMFactory2(new MVVMRepository2(this))).get(MVVMViewModel2.class);

        viewModel2.getTestData().observe(this, new Observer<List<NameBean>>() {
            @Override
            public void onChanged(@Nullable List<NameBean> nameBeans) {
                mTextView2.setText("size is " + nameBeans.size());
            }
        });
    }

    @OnClick({R.id.btn_activity_mvvm1, R.id.btn_activity_mvvm2, R.id.btn_activity_mvvm3, R.id.btn_activity_mvvm4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_activity_mvvm1:
                viewModel1.getGankResData();
                break;
            case R.id.btn_activity_mvvm2:
                viewModel2.getTestData();
                break;
            case R.id.btn_activity_mvvm3:
                readyGo(TomFragment.class);
                break;
            case R.id.btn_activity_mvvm4:
                // readyGo(JerryFragment.class);
                break;
        }
    }
}
