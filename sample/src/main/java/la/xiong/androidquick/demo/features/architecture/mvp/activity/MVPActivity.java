package la.xiong.androidquick.demo.features.architecture.mvp.activity;

import android.os.Bundle;
import android.widget.TextView;

import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.mvp.BaseTActivity;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class MVPActivity extends BaseTActivity<MVPPresenter> implements MVPContract.View{

    private TextView mContent;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_architecture_mvp_activity;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        mContent = findViewById(R.id.tv_activity_mvp);
        mContent.setText("this is MVPActivity");
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
