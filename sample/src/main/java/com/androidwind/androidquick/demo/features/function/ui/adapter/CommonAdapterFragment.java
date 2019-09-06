package com.androidwind.androidquick.demo.features.function.ui.adapter;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.androidwind.androidquick.demo.R;
import com.androidwind.androidquick.demo.base.BaseFragment;
import com.androidwind.androidquick.demo.bean.ABean;
import com.androidwind.androidquick.ui.adapter.CommonAdapter;
import com.androidwind.androidquick.ui.adapter.CommonViewHolder;
import com.androidwind.androidquick.util.LogUtil;
import com.androidwind.androidquick.util.ToastUtil;
import com.androidwind.annotation.annotation.BindTag;
import com.androidwind.annotation.annotation.TagInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@BindTag(type = TagInfo.Type.FRAGMENT, tags = {"adapter"}, description = "CommonAdapter实例")
public class CommonAdapterFragment extends BaseFragment {

    private static String TAG = "AdapterActivity";

    @BindView(R.id.rv_adapter)
    RecyclerView mRecyclerView;

    private CommonAdapter mCommonAdapter;

    private List<ABean> mABeanList;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_adapter;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        mABeanList = new ArrayList<ABean>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mCommonAdapter = new CommonAdapter<ABean>(getActivity(), R.layout.item_common_adapter_1, mABeanList) {
            @Override
            public void convert(CommonViewHolder holder, final ABean bean) {
                holder.setText(R.id.tv_name, bean.getName());
                holder.setText(R.id.tv_age, bean.getAge() + "");
                holder.setOnClickListener(R.id.ll_rv_common_adapter_item, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LogUtil.d(TAG, "onItemClick");
                        ToastUtil.showToast(bean.getName() + " clicked!");
                        notifyDataSetChanged();
                    }
                });
            }
        };
        mRecyclerView.setAdapter(mCommonAdapter);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                mABeanList.add(new ABean("tom", 18));
                mABeanList.add(new ABean("jerry", 20));
                mCommonAdapter.notifyDataSetChanged();
            }
        }, 1000);
    }
}
