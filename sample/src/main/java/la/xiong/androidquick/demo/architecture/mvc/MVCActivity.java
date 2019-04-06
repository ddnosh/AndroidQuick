package la.xiong.androidquick.demo.architecture.mvc;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import la.xiong.androidquick.demo.MyApplication;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseActivity;
import la.xiong.androidquick.demo.module.network.retrofit.GankApis;
import la.xiong.androidquick.demo.module.network.retrofit.GankRes;
import la.xiong.androidquick.module.network.retrofit.RetrofitManager;
import la.xiong.androidquick.module.network.retrofit.exeception.ApiException;
import la.xiong.androidquick.module.rxjava.BaseObserver;
import la.xiong.androidquick.tool.LogUtil;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class MVCActivity extends BaseActivity {

    RetrofitManager mRetrofitManager;
    @BindView(R.id.tv_activity_mvc)
    TextView mTextView;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_architecture_mvc_activity;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        mTextView.setText("I don't do anything, but this is a MVC show anyway. Click me!");
        mRetrofitManager = new RetrofitManager();
    }

    @OnClick(R.id.tv_activity_mvc)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_activity_mvc:
                mRetrofitManager.createApi(MyApplication.getInstance().getApplicationContext(), GankApis.class)
                        .getHistoryDate()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseObserver<GankRes<List<String>>>() {

                            @Override
                            public void onError(ApiException exception) {
                                LogUtil.e(TAG, "error:" + exception.getMessage());
                            }

                            @Override
                            public void onSuccess(GankRes<List<String>> listGankRes) {
                                LogUtil.i(TAG, listGankRes.getResults().toString());
                                if (!MVCActivity.this.isFinishing() && !MVCActivity.this.isDestroyed()) {
                                    mTextView.setText("yes, I get it from net!");
                                }
                            }
                        });
                break;
        }
    }
}
