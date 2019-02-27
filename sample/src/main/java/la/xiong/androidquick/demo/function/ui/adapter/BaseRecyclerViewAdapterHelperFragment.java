package la.xiong.androidquick.demo.function.ui.adapter;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.adapter.DemoAdapter;
import la.xiong.androidquick.demo.base.BaseFragment;
import la.xiong.androidquick.demo.bean.CBean;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class BaseRecyclerViewAdapterHelperFragment extends BaseFragment {

    private static String TAG = "AdapterActivity";

    @BindView(R.id.rv_adapter)
    RecyclerView mRecyclerView;

    private DemoAdapter mDemoAdapter;

    private List<CBean> mCBeanList;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_commonadapter;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
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
        mDemoAdapter = new DemoAdapter(mCBeanList);
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