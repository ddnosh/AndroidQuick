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
import la.xiong.androidquick.demo.features.architecture.design_patterns.factory.FactoryFragment;
import la.xiong.androidquick.demo.features.architecture.design_patterns.proxy.ProxyFragment;
import la.xiong.androidquick.demo.features.architecture.design_patterns.single.SingleFragment;
import la.xiong.androidquick.demo.features.architecture.mvc.MVCActivity;
import la.xiong.androidquick.demo.features.architecture.mvp.activity.MVPActivity;
import la.xiong.androidquick.demo.features.architecture.mvp.activity_dagger.MVPDaggerActivity;
import la.xiong.androidquick.demo.features.architecture.mvp.fragment_dagger.MVPDaggerFragment;
import la.xiong.androidquick.demo.features.architecture.mvvm.activity.MVVMActivity;
import la.xiong.androidquick.demo.features.architecture.segment.type1.Architecture1Fragment;
import la.xiong.androidquick.demo.features.architecture.segment.type2.Architecture2Fragment;
import la.xiong.androidquick.demo.features.architecture.segment.type3.WebViewJavascriptActivity;
import la.xiong.androidquick.demo.base.BaseActivity;
import la.xiong.androidquick.demo.bean.MenuBean;
import la.xiong.androidquick.demo.features.function.json.JsonFragment;
import la.xiong.androidquick.demo.features.function.permission.PermissionActivity;
import la.xiong.androidquick.demo.features.function.permission.PermissionFragment;
import la.xiong.androidquick.demo.features.function.rxjava.RxjavaFragment;
import la.xiong.androidquick.demo.features.function.sharedpreferences.SPFragment;
import la.xiong.androidquick.demo.features.function.ui.adapter.BaseRecyclerViewAdapterHelperFragment;
import la.xiong.androidquick.demo.features.function.ui.adapter.CommonAdapterFragment;
import la.xiong.androidquick.demo.features.function.ui.adapter.MultiViewTypeAdapterFragment;
import la.xiong.androidquick.demo.features.function.ui.banner.BannerFragment;
import la.xiong.androidquick.demo.features.function.ui.barbottom.BottomBarFragment;
import la.xiong.androidquick.demo.features.function.ui.barbottom.RadioButtonFragment;
import la.xiong.androidquick.demo.features.function.ui.bartop.CommonToolBarFragment;
import la.xiong.androidquick.demo.features.function.ui.bartop.ToolbarActivity;
import la.xiong.androidquick.demo.features.function.ui.constraintlayout.ConstraintLayoutFragment;
import la.xiong.androidquick.demo.features.function.ui.databinding.DatabindingFragment;
import la.xiong.androidquick.demo.features.function.ui.dialog.dialogfragment.DialogFragmentDemo;
import la.xiong.androidquick.demo.features.function.ui.fragment.CommonFragment;
import la.xiong.androidquick.demo.features.function.ui.fragment.FragmentationActivity;
import la.xiong.androidquick.demo.features.function.ui.refresh.SmartRefreshLayoutFragment;
import la.xiong.androidquick.demo.features.function.ui.refresh.SwipeRefreshLayoutFragment;
import la.xiong.androidquick.demo.features.function.ui.resolution.ResolutionAdaptionFragment;
import la.xiong.androidquick.demo.features.function.ui.tab.TabFTLFragment;
import la.xiong.androidquick.demo.features.function.ui.tab.TabLayoutFragment;
import la.xiong.androidquick.demo.features.function.ui.tab.TabSTLFragment;
import la.xiong.androidquick.demo.features.function.ui.varypagestatus.VaryPageStatusFragment;
import la.xiong.androidquick.demo.features.function.ui.webview.WebViewActivity;
import la.xiong.androidquick.demo.features.module.bus.eventbus.EventBusFragment;
import la.xiong.androidquick.demo.features.module.bus.rxbus.RxBusFragment;
import la.xiong.androidquick.demo.features.module.db.greendao.GreenDaoFragment;
import la.xiong.androidquick.demo.features.module.db.ormlite.OrmLiteFragment;
import la.xiong.androidquick.demo.features.module.image.glide.GlideFragment;
import la.xiong.androidquick.demo.features.module.ioc.butterknife.ButterKnifeFragment;
import la.xiong.androidquick.demo.features.module.ioc.dagger2.Dagger2Fragment;
import la.xiong.androidquick.demo.features.module.network.common.CommonHttpFragment;
import la.xiong.androidquick.demo.features.module.network.retrofit.NetworkActivity;
import la.xiong.androidquick.demo.features.module.network.retrofit.network1.Network1Fragment;
import la.xiong.androidquick.demo.features.module.network.retrofit.network2.Network2Fragment;
import la.xiong.androidquick.demo.features.module.task.TaskRxJavaFragment;
import la.xiong.androidquick.demo.features.module.task.TaskTinyFragment;
import la.xiong.androidquick.demo.features.other.callback.CallBackFragment;
import la.xiong.androidquick.demo.features.other.code.CodeFragment;
import la.xiong.androidquick.demo.features.other.lambda.LambdaFragment;
import la.xiong.androidquick.demo.features.other.rxlifecycle.RxLifecycleFragment;
import la.xiong.androidquick.demo.tool.MenuUtil;
import la.xiong.androidquick.tool.AppUtil;
import la.xiong.androidquick.tool.ToastUtil;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class MainActivity extends BaseActivity implements TreeNode.TreeNodeClickListener {

    private static String TAG = "MVVMActivity";

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
    protected void initViewsAndEvents(Bundle savedInstanceState) {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
                readyGo(MVPActivity.class);
            } else if (name.equals("MVP with Dagger2 for Activity")) {
                readyGo(MVPDaggerActivity.class);
            } else if (name.equals("MVP for Fragment")) {
                readyGo(MVPDaggerFragment.class);
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
            } else if (name.equals("GreenDao")) {
                readyGo(GreenDaoFragment.class);
            } else if (name.equals("Glide")) {
                readyGo(GlideFragment.class);
            } else if (name.equals("EventBus")) {
                readyGo(EventBusFragment.class);
            } else if (name.equals("ButterKnife")) {
                readyGo(ButterKnifeFragment.class);
            } else if (name.equals("Dagger2")) {
                readyGo(Dagger2Fragment.class);
            } else if (name.equals("CommonFragment")) {
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
                showLoadingDialog();
            } else if (name.equals("CommonDialog")) {
                getDialogBuilder(MainActivity.this)
                        .setTitle(R.string.app_name)
                        .setMessage("this is an information")
                        .setPositiveButton("Confirm")
                        .setNegativeButton("Cancel")
                        .setBtnClickCallBack(isConfirm -> {
                            if (isConfirm) {
                                ToastUtil.showToast("Confirm clicked");
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
            } else if (name.equals("SwipeRefreshLayout")) {
                readyGo(SwipeRefreshLayoutFragment.class);
            } else if (name.equals("SmartRefreshLayout")) {
                readyGo(SmartRefreshLayoutFragment.class);
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
            } else if (name.equals("OneLayout-MultipleViews")) {
                readyGo(Architecture1Fragment.class);
            } else if (name.equals("OneView-MultipleModules")) {
                readyGo(Architecture2Fragment.class);
            } else if (name.equals("WebView-JavaScripts")) {
                readyGo(WebViewJavascriptActivity.class);
            } else if (name.equals("Task-Tiny")) {
                readyGo(TaskTinyFragment.class);
            } else if (name.equals("Task-RxJava")) {
                readyGo(TaskRxJavaFragment.class);
            } else if (name.equals("RxLifecycle")) {
                readyGo(RxLifecycleFragment.class);
            } else if (name.equals("Lambda")) {
                readyGo(LambdaFragment.class);
            } else if (name.equals("OrmLite")) {
                readyGo(OrmLiteFragment.class);
            } else if (name.equals("DialogFragment")) {
                readyGo(DialogFragmentDemo.class);
            } else if (name.equals("Resolution")) {
                readyGo(ResolutionAdaptionFragment.class);
            } else if (name.equals("ConstraintLayout")) {
                readyGo(ConstraintLayoutFragment.class);
            } else if (name.equals("TabLayout")) {
                readyGo(TabLayoutFragment.class);
            } else if (name.equals("QuickGank")) {
                Bundle bundle = new Bundle();
                bundle.putString("url", "https://github.com/ddnosh/QuickGank");
                readyGo(WebViewActivity.class, bundle);
            } else if (name.equals("QuickTV")) {
                Bundle bundle = new Bundle();
                bundle.putString("url", "https://github.com/ddnosh/QuickTV");
                readyGo(WebViewActivity.class, bundle);
            } else if (name.equals("CallBack")) {
                readyGo(CallBackFragment.class);
            } else if (name.equals("MVC")) {
                readyGo(MVCActivity.class);
            } else if (name.equals("RxBus")) {
                readyGo(RxBusFragment.class);
            } else if (name.equals("MVVM for Activity")) {
                readyGo(MVVMActivity.class);
            } else if (name.equals("Factory Method")) {
                readyGo(FactoryFragment.class);
            } else if (name.equals("Facade")) {
                readyGo(FactoryFragment.class);
            } else if (name.equals("Proxy(with AOP)")) {
                readyGo(ProxyFragment.class);
            } else if (name.equals("SingleInstance")) {
                readyGo(SingleFragment.class);
            }
        }
    }
}
