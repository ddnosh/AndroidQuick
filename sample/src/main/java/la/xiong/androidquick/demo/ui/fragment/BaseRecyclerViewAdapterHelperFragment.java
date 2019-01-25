package la.xiong.androidquick.demo.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.adapter.DemoAdapter;
import la.xiong.androidquick.demo.base.BaseFragment;
import la.xiong.androidquick.demo.bean.BBean;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class BaseRecyclerViewAdapterHelperFragment extends BaseFragment {

    private static String TAG = "AdapterActivity";

    @BindView(R.id.rv_adapter)
    RecyclerView mRecyclerView;

    private DemoAdapter mDemoAdapter;

    private List<BBean> mBBeanList;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_commonadapter;
    }

    @Override
    protected void initViewsAndEvents() {
        mBBeanList = new ArrayList<BBean>();
        BBean b1 = new BBean("111");
        b1.itemType = 1;
        mBBeanList.add(b1);
        BBean b2 = new BBean("222");
        b2.itemType = 2;
        mBBeanList.add(b2);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mDemoAdapter = new DemoAdapter(getActivity(), mBBeanList));
        View header = mainLayout.inflate(getActivity(), R.layout.item_headerview, null);
        mDemoAdapter.addHeaderView(header);
    }

}