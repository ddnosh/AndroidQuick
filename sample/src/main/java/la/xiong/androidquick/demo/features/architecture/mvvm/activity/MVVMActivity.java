package la.xiong.androidquick.demo.features.architecture.mvvm.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.androidwind.annotation.annotation.BindTag;
import com.androidwind.annotation.annotation.TagInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseActivity;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@BindTag(type = TagInfo.Type.ACTIVITY, tags = {"MVVM"}, description = "Activity + MVVM实例")
public class MVVMActivity extends BaseActivity {

    @BindView(R.id.tv_activity_mvvm)
    TextView mTextView;

    private MVVMViewModel viewModel;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_architecture_mvvm_activity;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this, new MVVMFactory(new MVVMRepository(), this)).get(MVVMViewModel.class);

        viewModel.getData().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> s) {
                mTextView.setText("size is " + s.size());
            }
        });
    }

    @OnClick(R.id.btn_activity_mvvm)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_activity_mvvm:
                viewModel.getGankResData();
                break;
        }
    }
}
