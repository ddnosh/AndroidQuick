package la.xiong.androidquick.demo.adapter.provider;

import android.widget.Toast;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;

import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.adapter.DemoAdapter;
import la.xiong.androidquick.demo.bean.CBean;

/**
 * https://github.com/chaychan
 *
 * @author ChayChan
 * @description: Img ItemProvider
 * @date 2018/3/30  11:39
 */

public class ImgItemProvider extends BaseItemProvider<CBean, BaseViewHolder> {

    @Override
    public int viewType() {
        return DemoAdapter.TYPE_IMG;
    }

    @Override
    public int layout() {
        return R.layout.item_multiviewtype_img;
    }

    @Override
    public void convert(BaseViewHolder helper, CBean data, int position) {
        helper.addOnClickListener(R.id.iv_img);
        helper.setImageResource(R.id.iv_img, R.mipmap.ic_launcher);
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
