package la.xiong.androidquick.demo.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.unnamed.b.atv.holder.IconTreeItemHolder;
import com.unnamed.b.atv.holder.SelectableHeaderHolder;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.util.List;

import butterknife.BindView;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseActivity;
import la.xiong.androidquick.demo.bean.MenuBean;
import la.xiong.androidquick.demo.function.json.JsonFragment;
import la.xiong.androidquick.demo.function.permission.PermissionActivity;
import la.xiong.androidquick.demo.function.permission.PermissionFragment;
import la.xiong.androidquick.demo.function.ui.adapter.BaseRecyclerViewAdapterHelperFragment;
import la.xiong.androidquick.demo.function.ui.adapter.CommonAdapterFragment;
import la.xiong.androidquick.demo.function.ui.adapter.MultiViewTypeAdapterFragment;
import la.xiong.androidquick.demo.function.ui.bartop.CommonToolBarFragment;
import la.xiong.androidquick.demo.function.ui.bartop.ToolbarActivity;
import la.xiong.androidquick.demo.function.ui.fragment.CommonFragment;
import la.xiong.androidquick.demo.function.ui.fragment.FragmentationActivity;
import la.xiong.androidquick.demo.function.ui.tab.TabFTLFragment;
import la.xiong.androidquick.demo.function.ui.tab.TabSTLFragment;
import la.xiong.androidquick.demo.function.ui.webview.WebViewActivity;
import la.xiong.androidquick.demo.module.bus.BusActivity;
import la.xiong.androidquick.demo.module.db.DatabaseActivity;
import la.xiong.androidquick.demo.module.image.ImageActivity;
import la.xiong.androidquick.demo.module.ioc.IocActivity;
import la.xiong.androidquick.demo.module.mvp.activity.MvpActivity;
import la.xiong.androidquick.demo.module.mvp.fragment.MvpFragment;
import la.xiong.androidquick.demo.module.network.common.CommonHttpFragment;
import la.xiong.androidquick.demo.module.network.retrofit.NetworkActivity;
import la.xiong.androidquick.demo.module.network.retrofit.network1.Network1Fragment;
import la.xiong.androidquick.demo.module.network.retrofit.network2.Network2Fragment;
import la.xiong.androidquick.demo.tool.MenuUtil;
import la.xiong.androidquick.demo.function.ui.banner.BannerFragment;
import la.xiong.androidquick.demo.function.ui.barbottom.BottomBarFragment;
import la.xiong.androidquick.demo.ui.fragment.CodeFragment;
import la.xiong.androidquick.demo.function.ui.databinding.DatabindingFragment;
import la.xiong.androidquick.demo.ui.fragment.Example1Fragment;
import la.xiong.androidquick.demo.function.ui.varypagestatus.VaryPageStatusFragment;
import la.xiong.androidquick.demo.function.ui.barbottom.RadioButtonFragment;
import la.xiong.androidquick.demo.function.rxjava.RxjavaFragment;
import la.xiong.androidquick.demo.function.sharedpreferences.SPFragment;
import la.xiong.androidquick.tool.AppUtil;
import la.xiong.androidquick.tool.DialogUtil;
import la.xiong.androidquick.tool.ToastUtil;
import la.xiong.androidquick.ui.dialog.CommonDialog;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class MainActivity extends BaseActivity implements TreeNode.TreeNodeClickListener {

    private static String TAG = "MainActivity";

    private long DOUBLE_CLICK_TIME = 0L;

    @BindView(R.id.container)
    RelativeLayout mContainer;
    @BindView(R.id.tv_main_version)
    TextView tvMainVersion;

    private TreeNode root;
    private AndroidTreeView tView;
    private List<MenuBean> menuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViewsAndEvents() {
        root = TreeNode.root();
        menuList = MenuUtil.getPositions(getApplicationContext(), "menu.txt");
        initMenus();
        //init AndroidTreeView
        tView = new AndroidTreeView(getApplicationContext(), root);
        tView.setDefaultAnimation(true);
        tView.setUse2dScroll(true);
        tView.setDefaultContainerStyle(R.style.TreeNodeStyleCustom);
        tView.setDefaultNodeClickListener(this);
        mContainer.addView(tView.getView());
        //version
        String versionStr = getResources().getString(R.string.version);
        String version = String.format(versionStr, AppUtil.getVersionName(this));
        tvMainVersion.setText(version);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_MENU) {
            return true;
        } else if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            long currentTime = System.currentTimeMillis();
            if ((currentTime - DOUBLE_CLICK_TIME) > 2000) {
                ToastUtil.showToast("Press again to exit!");
                DOUBLE_CLICK_TIME = currentTime;
            } else {
                System.gc();
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initMenus() {
        for (MenuBean bean : menuList) {
            getTreeNode(bean);
        }
    }

    private TreeNode getTreeNode(MenuBean menuBean) {
        //root
        if (menuBean.upperId == 0) {
            return menuBean.treeNode = addTreeNode(root, menuBean.name);
        } else {
            for (MenuBean bean : menuList) {
                if (bean.currentId == menuBean.upperId) {
                    if (bean.treeNode == null) {
                        return menuBean.treeNode = getTreeNode(menuBean);
                    } else {
                        return menuBean.treeNode = addTreeNode(bean.treeNode, menuBean.name);
                    }
                }
            }
        }
        return null;
    }


    private TreeNode addTreeNode(TreeNode treeNode, String nodeName) {
        if (treeNode == null) return null;
        TreeNode node = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_folder, nodeName)).setViewHolder(
                new SelectableHeaderHolder(getApplicationContext()));
        treeNode.addChild(node);
        return node;
    }

    @Override
    public void onClick(TreeNode node, Object value) {
        String name = ((IconTreeItemHolder.IconTreeItem) value).text;
        if (!TextUtils.isEmpty(name)) {
            if (name.equals("MVP for Activity")) {
                readyGo(MvpActivity.class);
            } else if (name.equals("MVP for Fragment")) {
                readyGo(MvpFragment.class);
            } else if (name.equals("Common Http")) {
                readyGo(CommonHttpFragment.class);
            } else if (name.equals("Retrofit+CommonUrl")) {
                readyGo(Network1Fragment.class);
            } else if (name.equals("Retrofit+DifferentUrl")) {
                readyGo(Network2Fragment.class);
            } else if (name.equals("Retrofit+Download")) {
                readyGo(NetworkActivity.class);
            } else if (name.equals("Retrofit+CommonUrl+Get")) {
                Bundle bundle1 = new Bundle();
                bundle1.putString("type", "get");
                readyGo(Network1Fragment.class, bundle1);
            } else if (name.equals("Database")) {
                readyGo(DatabaseActivity.class);
            } else if (name.equals("Image")) {
                readyGo(ImageActivity.class);
            } else if (name.equals("Bus")) {
                readyGo(BusActivity.class);
            } else if (name.equals("IOC")) {
                readyGo(IocActivity.class);
            } if (name.equals("CommonFragment")) {
                readyGo(CommonFragment.class);
            } else if (name.equals("Fragmentation")) {
                readyGo(FragmentationActivity.class);
            } else if (name.equals("CommonAdapter")) {
                readyGo(CommonAdapterFragment.class);
            } else if (name.equals("MultiViewTypeAdapter")) {
                readyGo(MultiViewTypeAdapterFragment.class);
            } else if (name.equals("BaseRecycleViewAdapterHelper")) {
                readyGo(BaseRecyclerViewAdapterHelperFragment.class);
            } else if (name.equals("DefaultToolbar")) {
                readyGo(ToolbarActivity.class);
            } else if (name.equals("CommonToolbar")) {
                readyGo(CommonToolBarFragment.class);
            } else if (name.equals("RadioButton")) {
                readyGo(RadioButtonFragment.class);
            } else if (name.equals("BottomBar")) {
                readyGo(BottomBarFragment.class);
            } else if (name.equals("LoadingDialog")) {
                DialogUtil.showLoadingDialog(getApplicationContext());
            } else if (name.equals("CommonDialog")) {
                DialogUtil.getDialogBuilder(getApplicationContext())
                        .setTitle(R.string.app_name)
                        .setMessage("this is an information")
                        .setPositiveButton("Confirm")
                        .setNegativeButton("Cancel")
                        .setBtnClickCallBack(new CommonDialog.DialogBtnCallBack() {
                            @Override
                            public void onDialogButClick(boolean isConfirm) {
                                if (isConfirm) {
                                    ToastUtil.showToast("Confirm clicked");
                                }
                            }
                        }).show();
            } else if (name.equals("SmartTabLayout")) {
                readyGo(TabSTLFragment.class);
            } else if (name.equals("FlycoTabLayout")) {
                readyGo(TabFTLFragment.class);
            } else if (name.equals("Banner")) {
                readyGo(BannerFragment.class);
            } else if (name.equals("Code")) {
                readyGo(CodeFragment.class);
            } else if (name.equals("Permission-Camera")) {
                readyGo(PermissionActivity.class);
            } else if (name.equals("Permission-Fragment-Call")) {
                readyGo(PermissionFragment.class);
            } else if (name.equals("Refresh")) {
                readyGo(Example1Fragment.class);
            } else if (name.equals("WebView")) {
                Bundle bundle = new Bundle();
                bundle.putString("url", "https://github.com/ddnosh");
                readyGo(WebViewActivity.class, bundle);
            } else if (name.equals("DataBinding")) {
                readyGo(DatabindingFragment.class);
            } else if (name.equals("VaryPageStatus")) {
                readyGo(VaryPageStatusFragment.class);
            } else if (name.equals("Json")) {
                readyGo(JsonFragment.class);
            } else if (name.equals("Rxjava")) {
                readyGo(RxjavaFragment.class);
            } else if (name.equals("SharedPreferences")) {
                readyGo(SPFragment.class);
            } else if (name.equals("More: AndroidUtilCode")) {
                Bundle bundle = new Bundle();
                bundle.putString("url", "https://github.com/Blankj/AndroidUtilCode");
                readyGo(WebViewActivity.class, bundle);
            } else if (name.equals("Architecture")) {
                readyGo(ArchitectureActivity.class);
            }
        }
    }
}
