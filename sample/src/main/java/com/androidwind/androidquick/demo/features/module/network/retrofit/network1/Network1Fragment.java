package com.androidwind.androidquick.demo.features.module.network.retrofit.network1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.androidwind.androidquick.demo.R;
import com.androidwind.androidquick.demo.base.mvp_dagger2.BaseTFragment;
import com.androidwind.androidquick.util.LogUtil;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import butterknife.BindView;


/**
 * (Retrofit+OkHttp+RxJava)Common Url
 *
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class Network1Fragment extends BaseTFragment<Network1Presenter> implements Network1Contract.View{

    protected static String TAG = "NetworkActivity";

    @BindView(R.id.container)
    ScrollView mContainer;
    @BindView(R.id.tv_network_result)
    TextView tvNetworkResult;

    public static final String MESSAGE_PROGRESS = "message_progress";
    private LocalBroadcastManager bManager;

    private Bundle mBundle;

    @Override
    protected void initInjector() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_network1;
    }

    @Override
    protected View setDefaultVaryViewRoot() {
        return mContainer;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        mBundle = getActivity().getIntent().getExtras();
//        toggleShowLoading(true, "loading...");
        mPresenter.initData(mBundle.getString("type"));
    }

    @Override
    public void updateView(String result) {
//        toggleShowLoading(false, "loading...");
        LogUtil.i(TAG, "updateView");
        tvNetworkResult.setText(result);
    }

    @Override
    public void downloadCompleted(String path) {
//        toggleShowLoading(false, "loading...");
        Log.d(TAG, "downloadAPK completed: " + path);
//        FileUtil.installApk(getActivity(), path);
    }

}
