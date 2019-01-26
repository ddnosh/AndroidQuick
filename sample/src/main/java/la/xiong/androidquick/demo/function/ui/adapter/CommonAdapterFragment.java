package la.xiong.androidquick.demo.function.ui.adapter;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseFragment;
import la.xiong.androidquick.demo.bean.ABean;
import la.xiong.androidquick.tool.LogUtil;
import la.xiong.androidquick.tool.ToastUtil;
import la.xiong.androidquick.ui.adapter.CommonAdapter;
import la.xiong.androidquick.ui.adapter.CommonViewHolder;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class CommonAdapterFragment extends BaseFragment {

    private static String TAG = "AdapterActivity";

    @BindView(R.id.rv_adapter)
    RecyclerView mRecyclerView;

    private CommonAdapter mCommonAdapter;

    private List<ABean> mABeanList;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_commonadapter;
    }

    @Override
    protected void initViewsAndEvents() {
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
