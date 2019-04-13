package la.xiong.androidquick.demo.features.function.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseFragment;
import la.xiong.androidquick.demo.bean.BBean;
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

    private List<BBean> mBBeanList;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_adapter;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        mBBeanList = new ArrayList<BBean>();
        mBBeanList.add(new BBean("left", "here is left loaded"));
        mBBeanList.add(new BBean("right", "https://www.baidu.com/img/bd_logo1.png"));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(new ChatAdapter(getActivity(), mBBeanList));
    }

    class ChatAdapter extends MultiItemCommonAdapter<BBean> {

        public ChatAdapter(Context context, List<BBean> datas) {
            super(context, datas, new MultiItemTypeSupport<BBean>() {
                @Override
                public int getLayoutId(int itemType) {
                    if (itemType == 1) {
                        return R.layout.item_multiviewtype_text;
                    } else {
                        return R.layout.item_multiviewtype_img;
                    }
                }

                @Override
                public int getItemViewType(int position, BBean bean) {
                    if (bean.getType().equals("left")) {
                        return 1;
                    }
                    return 2;
                }

            });

        }

        @Override
        public void convert(CommonViewHolder holder, BBean bean) {
            if (holder.getItemViewType() == 1) {
                holder.setText(R.id.tv_text, bean.getValue());
            } else {
                holder.setImageResourceWithGlide(R.id.iv_img, bean.getValue());
            }
        }
    }
}