package la.xiong.androidquick.demo.features.function.ui.resolution;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseFragment;
import la.xiong.androidquick.demo.features.function.ui.webview.WebViewActivity;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class ResolutionAdaptionFragment extends BaseFragment {

    @BindView(R.id.btn_click)
    Button btnClick;

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_resolutionadaption;
    }

    @OnClick(R.id.btn_click)
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("url", "https://blog.csdn.net/ddnosh/article/details/78941302");
        readyGo(WebViewActivity.class, bundle);
    }
}
