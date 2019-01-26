package la.xiong.androidquick.demo.function.ui.varypagestatus;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseFragment;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class VaryPageStatusFragment extends BaseFragment {
    @BindView(R.id.btn_ui_pagestatus_loading)
    Button mLoading;
    @BindView(R.id.btn_ui_pagestatus_empty)
    Button mEmpty;
    @BindView(R.id.btn_ui_pagestatus_error)
    Button mError;
    @BindView(R.id.btn_ui_pagestatus_networkerror)
    Button mNetworkError;
    @BindView(R.id.container)
    LinearLayout mContainer;

    @Override
    protected void initViewsAndEvents() {

    }

    @Override
    protected View getLoadingTargetView() {
        return mContainer;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_pagestatus;
    }

    @OnClick({R.id.btn_ui_pagestatus_loading, R.id.btn_ui_pagestatus_empty, R.id.btn_ui_pagestatus_error, R.id.btn_ui_pagestatus_networkerror})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ui_pagestatus_loading:
                toggleShowLoading(true, "try to load......");
                break;
            case R.id.btn_ui_pagestatus_empty:
                toggleShowEmpty(true, "test", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getActivity(), "empty!", Toast.LENGTH_LONG).show();
                    }
                });
                break;
            case R.id.btn_ui_pagestatus_error:
                toggleShowError(true, "error", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getActivity(), "error!", Toast.LENGTH_LONG).show();
                    }
                });
                break;
            case R.id.btn_ui_pagestatus_networkerror:
                toggleNetworkError(true, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getActivity(), "network error!", Toast.LENGTH_LONG).show();
                    }
                });
                break;
            default:
                break;
        }
    }

}
