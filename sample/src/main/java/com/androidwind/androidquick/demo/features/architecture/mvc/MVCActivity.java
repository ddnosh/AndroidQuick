package com.androidwind.androidquick.demo.features.architecture.mvc;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.androidwind.androidquick.demo.R;
import com.androidwind.androidquick.demo.base.BaseActivity;
import com.androidwind.androidquick.demo.features.module.network.retrofit.GankApis;
import com.androidwind.androidquick.demo.features.module.network.retrofit.GankRes;
import com.androidwind.androidquick.module.retrofit.RetrofitManager;
import com.androidwind.androidquick.module.retrofit.exeception.ApiException;
import com.androidwind.androidquick.module.rxjava.BaseObserver;
import com.androidwind.androidquick.util.LogUtil;
import com.androidwind.annotation.annotation.BindTag;
import com.androidwind.annotation.annotation.TagInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@BindTag(type = TagInfo.Type.ACTIVITY, tags = {"MVC"}, description = "Activity + MVC实例")
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
                mRetrofitManager.createApi(GankApis.class)
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
