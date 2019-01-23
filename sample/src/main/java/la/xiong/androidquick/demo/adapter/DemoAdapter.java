package la.xiong.androidquick.demo.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.bean.BBean;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */

public class DemoAdapter extends BaseMultiItemQuickAdapter<BBean, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    private Context mContext;

    public DemoAdapter(Context context, List<BBean> data) {
        super(data);
        addItemType(1, R.layout.item_multiviewtype_left);
        addItemType(2, R.layout.item_multiviewtype_right);
    }

    @Override
    protected void convert(BaseViewHolder helper, BBean item) {
        switch (helper.getItemViewType()) {
            case 1:
                helper.setText(R.id.tv_left, item.getValue());
                break;
            case 2:
                helper.setText(R.id.tv_right, item.getValue());
                break;
        }
    }
}
