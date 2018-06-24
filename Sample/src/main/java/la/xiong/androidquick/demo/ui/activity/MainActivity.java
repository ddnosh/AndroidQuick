package la.xiong.androidquick.demo.ui.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseActivity;
import la.xiong.androidquick.tool.AppUtil;
import la.xiong.androidquick.tool.ToastUtil;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class MainActivity extends BaseActivity {

    private static String TAG = "MainActivity";

    private long DOUBLE_CLICK_TIME = 0L;

    @BindView(R.id.container)
    RelativeLayout mContainer;
    @BindView(R.id.btn_main_ui)
    Button btnMainUI;
    @BindView(R.id.tv_main_version)
    TextView tvMainVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected View getLoadingTargetView() {
        return mContainer;
    }

    @Override
    protected void initViewsAndEvents() {
        String versionStr = getResources().getString(R.string.version);
        String version = String.format(versionStr, AppUtil.getVersionName(this));
        tvMainVersion.setText(version);
    }

    @OnClick({R.id.btn_main_ui, R.id.btn_main_network, R.id.btn_main_database, R.id.btn_main_tools, R.id.btn_main_task, R.id.btn_main_architecture})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.btn_main_ui:
                readyGo(UIActivity.class);
                break;
            case R.id.btn_main_network:
                readyGo(NetworkActivity.class);
                break;
            case R.id.btn_main_database:
                readyGo(DatabaseActivity.class);
                break;
            case R.id.btn_main_tools:
                readyGo(ToolsActivity.class);
                break;
            case R.id.btn_main_task:
                readyGo(TaskActivity.class);
                break;
            case R.id.btn_main_architecture:
                readyGo(ArchitectureActivity.class);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_MENU) {
            return true;
        } else if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            long currentTime = System.currentTimeMillis();
            if ((currentTime - DOUBLE_CLICK_TIME) > 2000) {
                ToastUtil.showToast("再按一次退出");
                DOUBLE_CLICK_TIME = currentTime;
            } else {
                System.gc();
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
