package la.xiong.androidquick.demo.module.mvp.activity;

import android.os.Bundle;
import android.widget.TextView;

import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseTActivity;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class MvpActivity extends BaseTActivity<MvpPresenter> implements MvpContract.View{

    private TextView mContent;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_module_mvp_activity;
    }

    @Override
    protected void initInjector() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        mContent = findViewById(R.id.tv_activity_mvp);
        mContent.setText("this is MvpActivity");
        mPresenter.initData();
    }

    @Override
    public void refreshView(final String result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mContent.setText(result);
            }
        });
    }
}
