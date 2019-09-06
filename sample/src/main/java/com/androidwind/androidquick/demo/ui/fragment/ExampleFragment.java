package com.androidwind.androidquick.demo.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.TextView;

import com.androidwind.androidquick.demo.R;
import com.androidwind.androidquick.demo.base.BaseFragment;
import com.androidwind.androidquick.util.StringUtil;
import com.androidwind.androidquick.util.ToastUtil;

import butterknife.BindView;


/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class ExampleFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    public static final String TAG = "ExampleFragment";

    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.textView)
    TextView mTextView;
    private String type;
    private boolean isRefresh = false;//是否刷新中

    public static ExampleFragment newInstance() {
        return new ExampleFragment();
    }

    public static ExampleFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString("Type", type);
        ExampleFragment fragment = new ExampleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {

        //设置进度条的颜色主题，最多能设置四种 加载颜色是循环播放的，只要没有完成刷新就会一直循环，holo_blue_bright>holo_green_light>holo_orange_light>holo_red_light
        // mSwipeLayout.setColorScheme(android.R.color.holo_blue_bright,
        //         android.R.color.holo_green_light,
        //         android.R.color.holo_orange_light,
        //         android.R.color.holo_red_light);

        //上面的方法已经废弃
        mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE,
                Color.GREEN,
                Color.YELLOW,
                Color.RED);


        // 设置手指在屏幕下拉多少距离会触发下拉刷新
        mSwipeRefreshLayout.setDistanceToTriggerSync(300);
        // 设定下拉圆圈的背景
        mSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.WHITE);
        // 设置圆圈的大小
        mSwipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);

        //设置下拉刷新的监听
        mSwipeRefreshLayout.setOnRefreshListener(this);


        if (getArguments() == null) return;
        type = getArguments().getString("Type");
        if (StringUtil.isEmpty(type))
            ToastUtil.showToast("this is a new way go");
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_example1;
    }

    Handler handler = new Handler();

    Runnable mRunnable = new Runnable() {
        public void run() {
            mTextView.setText("Refreshed!");
            //显示或隐藏刷新进度条
            mSwipeRefreshLayout.setRefreshing(false);
            isRefresh = false;
        }
    };

    @Override
    public void onRefresh() {
        //检查是否处于刷新状态
        if (!isRefresh) {
            isRefresh = true;
            //模拟加载网络数据，这里设置4秒，正好能看到4色进度条
            handler.postDelayed(mRunnable, 4000);
        }
    }

    @Override
    public void onDestroy() {
        //退出fragment后移除消息
        handler.removeCallbacks(mRunnable);
        super.onDestroy();
    }
}
