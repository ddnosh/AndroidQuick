package la.xiong.androidquick.demo.ui.fragment;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseFragment;
import la.xiong.androidquick.demo.bean.BBean;
import la.xiong.androidquick.ui.adapter.CommonAdapter;
import la.xiong.androidquick.ui.adapter.CommonViewHolder;
import la.xiong.androidquick.ui.adapter.MultiItemCommonAdapter;
import la.xiong.androidquick.ui.adapter.MultiItemTypeSupport;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class MultiViewTypeAdapterFragment extends BaseFragment {

    private static String TAG = "AdapterActivity";

    @BindView(R.id.rv_adapter)
    RecyclerView mRecyclerView;

    private CommonAdapter mCommonAdapter;

    private List<BBean> mBBeanList;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_commonadapter;
    }

    @Override
    protected void initViewsAndEvents() {
        mBBeanList = new ArrayList<BBean>();
        mBBeanList.add(new BBean("left", "here is left"));
        mBBeanList.add(new BBean("right", "here is right"));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(new ChatAdapter(getActivity(), mBBeanList));
    }

    class ChatAdapter extends MultiItemCommonAdapter<BBean> {

        public ChatAdapter(Context context, List<BBean> datas) {
            super(context, datas, new MultiItemTypeSupport<BBean>() {
                @Override
                public int getLayoutId(int itemType) {
                    if (itemType == 1)
                        return R.layout.item_multiviewtype_left;
                    else
                        return R.layout.item_multiviewtype_right;
                }

                @Override
                public int getItemViewType(int position, BBean bean) {
                    if (bean.getType().equals("left"))
                        return 1;
                    return 2;
                }

            });

        }

        @Override
        public void convert(CommonViewHolder holder, BBean bean) {

        }
    }
}