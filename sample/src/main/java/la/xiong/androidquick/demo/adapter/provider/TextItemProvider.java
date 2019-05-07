package la.xiong.androidquick.demo.adapter.provider;

import android.widget.Toast;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;

import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.adapter.DemoAdapter;
import la.xiong.androidquick.demo.bean.CBean;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class TextItemProvider extends BaseItemProvider<CBean, BaseViewHolder> {

    @Override
    public int viewType() {
        return DemoAdapter.TYPE_TEXT;
    }

    @Override
    public int layout() {
        return R.layout.item_multiviewtype_text;
    }

    @Override
    public void convert(BaseViewHolder helper, CBean data, int position) {
        helper.setText(R.id.tv_text, data.getText());
    }

    @Override
    public void onClick(BaseViewHolder helper, CBean data, int position) {
        Toast.makeText(mContext, "click", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onLongClick(BaseViewHolder helper, CBean data, int position) {
        Toast.makeText(mContext, "longClick", Toast.LENGTH_SHORT).show();
        return true;
    }
}
