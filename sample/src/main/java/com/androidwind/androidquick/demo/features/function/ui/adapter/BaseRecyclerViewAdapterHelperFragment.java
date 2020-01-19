package com.androidwind.androidquick.demo.features.function.ui.adapter;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.androidwind.androidquick.demo.R;
import com.androidwind.androidquick.demo.base.BaseFragment;
import com.androidwind.androidquick.demo.bean.CBean;
import com.androidwind.androidquick.demo.bean.HomeBean;
import com.androidwind.androidquick.util.ToastUtil;
import com.androidwind.annotation.annotation.BindTag;
import com.androidwind.annotation.annotation.TagInfo;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;


/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@BindTag(type = TagInfo.Type.FRAGMENT, tags = {"adapter"}, description = "BaseRecyclerViewAdapterHelper实例")
public class BaseRecyclerViewAdapterHelperFragment extends BaseFragment {

    private static String TAG = "AdapterActivity";

    @BindView(R.id.rv_adapter)
    RecyclerView mRecyclerView;
    @BindView(R.id.ll_brvah)
    LinearLayout mBRVAH;

    private List<HomeBean> mDataList;
    private List<CBean> mCBeanList;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_adapter;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        initView();
        initData();
    }

    @OnClick({R.id.btn_brvah_1, R.id.btn_brvah_2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_brvah_1:
                initAdapter1();
                break;
            case R.id.btn_brvah_2:
                initAdapter2();
                break;
        }
    }

    private void initView() {
        mBRVAH.setVisibility(View.VISIBLE);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
    }

    private void initData() {
        //demo 1
        mDataList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            HomeBean item = new HomeBean();
            item.setTitle("Title"+i);
            item.setImageResource(R.drawable.image_loading);
            mDataList.add(item);
        }
        //demo 2
        mCBeanList = new ArrayList<CBean>();
        CBean b1 = new CBean("this is a text", "");
        b1.itemType = 1;
        mCBeanList.add(b1);
        CBean b2 = new CBean("without a text", "https://www.baidu.com/img/bd_logo1.png");
        b2.itemType = 2;
        mCBeanList.add(b2);
        CBean b3 = new CBean("this is a text", "https://www.baidu.com/img/bd_logo1.png");
        b3.itemType = 3;
        mCBeanList.add(b3);
    }

    //demo 1
    private void initAdapter1() {
        BaseQuickAdapter homeAdapter = new HomeAdapter(R.layout.item_home_view, mDataList);
        homeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtil.showToast("position = " + position);
            }
        });
        homeAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.icon:
                        ToastUtil.showToast("picture clicked!");
                        break;
                }
            }
        });

        mRecyclerView.setAdapter(homeAdapter);
    }

    //demo 2
    private void initAdapter2() {
        DemoAdapter mDemoAdapter = new DemoAdapter(mCBeanList);
        View header = getLayoutInflater().inflate(R.layout.item_headerview, (ViewGroup) mRecyclerView.getParent(), false);
        mDemoAdapter.addHeaderView(header);
        View footer = getLayoutInflater().inflate(R.layout.item_footerview, (ViewGroup) mRecyclerView.getParent(), false);
        mDemoAdapter.addFooterView(footer);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mDemoAdapter);
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                CBean bean = (CBean) baseQuickAdapter.getItem(i);
                switch (view.getId()) {
                    case R.id.iv_img:
                        Toast.makeText(mContext, bean.getText(), Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

}