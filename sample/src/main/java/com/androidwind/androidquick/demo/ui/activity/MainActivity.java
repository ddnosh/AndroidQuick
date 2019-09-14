package com.androidwind.androidquick.demo.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidwind.androidquick.demo.R;
import com.androidwind.androidquick.demo.base.BaseActivity;
import com.androidwind.androidquick.demo.bean.MenuBean;
import com.androidwind.androidquick.demo.features.architecture.mvc.MVCActivity;
import com.androidwind.androidquick.demo.features.architecture.mvp.activity.MVPActivity;
import com.androidwind.androidquick.demo.features.architecture.mvp.activity_dagger.MVPDaggerActivity;
import com.androidwind.androidquick.demo.features.architecture.mvp.fragment_dagger.MVPDaggerFragment;
import com.androidwind.androidquick.demo.features.architecture.mvvm.activity.MVVMActivity;
import com.androidwind.androidquick.demo.features.architecture.segment.type1.Architecture1Fragment;
import com.androidwind.androidquick.demo.features.architecture.segment.type2.Architecture2Fragment;
import com.androidwind.androidquick.demo.features.architecture.segment.type3.WebViewJavascriptActivity;
import com.androidwind.androidquick.demo.features.design_patterns.adapter.AdapterFragment;
import com.androidwind.androidquick.demo.features.design_patterns.builder.BuilderFragment;
import com.androidwind.androidquick.demo.features.design_patterns.factory.FactoryFragment;
import com.androidwind.androidquick.demo.features.design_patterns.observer.ObserverFragment;
import com.androidwind.androidquick.demo.features.design_patterns.proxy.ProxyFragment;
import com.androidwind.androidquick.demo.features.design_patterns.responsibilitychain.ResponsibilityChainFragment;
import com.androidwind.androidquick.demo.features.design_patterns.single.SingleFragment;
import com.androidwind.androidquick.demo.features.design_patterns.strategy.StrategyFragment;
import com.androidwind.androidquick.demo.features.design_patterns.template.TemplateFragment;
import com.androidwind.androidquick.demo.features.function.annotation.AnnotationFragment;
import com.androidwind.androidquick.demo.features.function.immersion.ImmersionActivity;
import com.androidwind.androidquick.demo.features.function.json.JsonFragment;
import com.androidwind.androidquick.demo.features.function.permission.PermissionActivity;
import com.androidwind.androidquick.demo.features.function.permission.PermissionFragment;
import com.androidwind.androidquick.demo.features.function.rxjava.RxJavaFragment;
import com.androidwind.androidquick.demo.features.function.sharedpreferences.SPFragment;
import com.androidwind.androidquick.demo.features.function.ui.adapter.BaseRecyclerViewAdapterHelperFragment;
import com.androidwind.androidquick.demo.features.function.ui.adapter.CommonAdapterFragment;
import com.androidwind.androidquick.demo.features.function.ui.adapter.MultiViewTypeAdapterFragment;
import com.androidwind.androidquick.demo.features.function.ui.banner.BannerFragment;
import com.androidwind.androidquick.demo.features.function.ui.barbottom.BottomBarFragment;
import com.androidwind.androidquick.demo.features.function.ui.barbottom.RadioButtonFragment;
import com.androidwind.androidquick.demo.features.function.ui.bartop.CommonToolBarFragment;
import com.androidwind.androidquick.demo.features.function.ui.bartop.ToolbarActivity;
import com.androidwind.androidquick.demo.features.function.ui.constraintlayout.ConstraintLayoutFragment;
import com.androidwind.androidquick.demo.features.function.ui.databinding.DataBindingFragment;
import com.androidwind.androidquick.demo.features.function.ui.dialog.dialogfragment.DialogFragmentDemo;
import com.androidwind.androidquick.demo.features.function.ui.fragment.CommonFragment;
import com.androidwind.androidquick.demo.features.function.ui.fragment.FragmentationActivity;
import com.androidwind.androidquick.demo.features.function.ui.refresh.SmartRefreshLayoutFragment;
import com.androidwind.androidquick.demo.features.function.ui.refresh.SwipeRefreshLayoutFragment;
import com.androidwind.androidquick.demo.features.function.ui.resolution.ResolutionAdaptionFragment;
import com.androidwind.androidquick.demo.features.function.ui.tab.TabFTLFragment;
import com.androidwind.androidquick.demo.features.function.ui.tab.TabLayoutFragment;
import com.androidwind.androidquick.demo.features.function.ui.tab.TabSTLFragment;
import com.androidwind.androidquick.demo.features.function.ui.varypagestatus.VaryPageStatusFragment;
import com.androidwind.androidquick.demo.features.function.ui.webview.WebViewActivity;
import com.androidwind.androidquick.demo.features.module.bus.eventbus.EventBusFragment;
import com.androidwind.androidquick.demo.features.module.bus.rxbus.RxBusFragment;
import com.androidwind.androidquick.demo.features.module.db.greendao.GreenDaoFragment;
import com.androidwind.androidquick.demo.features.module.db.ormlite.OrmLiteFragment;
import com.androidwind.androidquick.demo.features.module.image.glide.GlideFragment;
import com.androidwind.androidquick.demo.features.module.ioc.butterknife.ButterKnifeFragment;
import com.androidwind.androidquick.demo.features.module.ioc.dagger2.Dagger2Fragment;
import com.androidwind.androidquick.demo.features.module.network.common.CommonHttpFragment;
import com.androidwind.androidquick.demo.features.module.network.retrofit.NetworkActivity;
import com.androidwind.androidquick.demo.features.module.network.retrofit.TestApis;
import com.androidwind.androidquick.demo.features.module.network.retrofit.network1.Network1Fragment;
import com.androidwind.androidquick.demo.features.module.network.retrofit.network2.Network2Fragment;
import com.androidwind.androidquick.demo.features.module.task.TaskRxJavaFragment;
import com.androidwind.androidquick.demo.features.module.task.TaskTinyFragment;
import com.androidwind.androidquick.demo.features.other.callback.CallBackFragment;
import com.androidwind.androidquick.demo.features.other.code.CodeFragment;
import com.androidwind.androidquick.demo.features.other.lambda.LambdaFragment;
import com.androidwind.androidquick.demo.features.other.rxlifecycle.RxJavaLifecycleFragment;
import com.androidwind.androidquick.demo.features.search.SearchAdapter;
import com.androidwind.androidquick.demo.features.search.SearchManager;
import com.androidwind.androidquick.demo.features.solution.aop.AOPFragment;
import com.androidwind.androidquick.demo.tool.MenuUtil;
import com.androidwind.androidquick.module.retrofit.RetrofitManager;
import com.androidwind.androidquick.module.retrofit.exeception.ApiException;
import com.androidwind.androidquick.module.rxjava.BaseObserver;
import com.androidwind.androidquick.util.AppUtil;
import com.androidwind.androidquick.util.RxUtil;
import com.androidwind.androidquick.util.StringUtil;
import com.androidwind.androidquick.util.ToastUtil;
import com.androidwind.annotation.core.SearchEngine;
import com.unnamed.b.atv.holder.IconTreeItemHolder;
import com.unnamed.b.atv.holder.SelectableHeaderHolder;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.util.List;

import butterknife.BindView;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


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
    @BindView(R.id.tv_sdk_version)
    TextView tvSdkVersion;

    private TreeNode root;
    private AndroidTreeView tView;
    private List<MenuBean> menuList;
    //search
    @BindView(R.id.sv_search)
    SearchView mSearchView;
    @BindView(R.id.lv_result)
    ListView mListView;
    private ArrayAdapter mAdapter;

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
        menuList = new MenuUtil().getPositions(getApplicationContext(), "menu.txt");
        initMenus();
        //init AndroidTreeView
        tView = new AndroidTreeView(getApplicationContext(), root);
        tView.setDefaultAnimation(true);
        tView.setUse2dScroll(true);
        tView.setDefaultContainerStyle(R.style.TreeNodeStyleCustom);
        tView.setDefaultNodeClickListener(this);
        mContainer.addView(tView.getView());
        //app version
        String versionStr = getResources().getString(R.string.app_version);
        getSdkVersion();
        String version = String.format(versionStr, AppUtil.getVersionName(getApplicationContext()));
        tvMainVersion.setText(version);
        //search
        mAdapter = new SearchAdapter(MainActivity.this, android.R.layout.simple_list_item_1, SearchManager.getInstance().searchResults);
        mListView.setAdapter(mAdapter);
        mListView.setTextFilterEnabled(true);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // ToastUtil.showToast("You clicked " + SearchManager.getInstance().searchResults.get(position));
                mSearchView.clearFocus();
                SearchEngine.getsInstance().jumpTo(MainActivity.this, SearchManager.getInstance().searchResults.get(position));
            }
        });

        mSearchView.setIconified(false);
        mSearchView.onActionViewExpanded();
        mSearchView.setIconifiedByDefault(false);
        mSearchView.clearFocus();
        mSearchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Open", Toast.LENGTH_SHORT).show();
            }
        });
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String keyword) {
                SearchManager.getInstance().searchResults.clear();
                if (StringUtil.isEmpty(keyword)) {
                    mListView.setVisibility(View.GONE);
                    return false;
                }
                SearchManager.getInstance().getResults(keyword);
                if (SearchManager.getInstance().searchResults.size() > 0) {
                    mListView.setVisibility(View.VISIBLE);
                    mAdapter.notifyDataSetChanged();
                } else {
                    mListView.setVisibility(View.GONE);
                }
                return false;
            }
        });
    }

    private void getSdkVersion() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .client(RetrofitManager.getInstance().getOkHttpClient())
                .addConverterFactory(ScalarsConverterFactory.create())//添加 string 转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//添加 RxJava 适配器
                .build();
        retrofit.create(TestApis.class)
                .getSdkVersion()
                .compose(RxUtil.<String>applySchedulers())
                .compose(this.<String>bindToLifecycle())
                .subscribe(new BaseObserver<String>() {

                               @Override
                               public void onError(ApiException exception) {

                               }

                               @Override
                               public void onSuccess(String html) {
                                   if (!StringUtil.isEmpty(html)) {
                                       if (html.contains("</text></g> </svg>")) {
                                           String[] sArray = html.split("</text></g> </svg>");
                                           int position = sArray[0].lastIndexOf(">");
                                           if (position > 0) {
                                               String sdk = sArray[0].substring(position + 1);
                                               String sdkVersion = getResources().getString(R.string.sdk_version);
                                               String version = String.format(sdkVersion, sdk);
                                               tvSdkVersion.setText(version);
                                           }
                                       }
                                   }
                               }
                           }
                );
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
        if (treeNode == null) {
            return null;
        }
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
            } else if (name.equals("Permission-Activity")) {
                readyGo(PermissionActivity.class);
            } else if (name.equals("Permission-Fragment")) {
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
                readyGo(DataBindingFragment.class);
            } else if (name.equals("VaryPageStatus")) {
                readyGo(VaryPageStatusFragment.class);
            } else if (name.equals("Json")) {
                readyGo(JsonFragment.class);
            } else if (name.equals("RxJava")) {
                readyGo(RxJavaFragment.class);
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
            } else if (name.equals("RxJava_Lifecycle")) {
                readyGo(RxJavaLifecycleFragment.class);
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
            } else if (name.equals("Factory")) {
                readyGo(FactoryFragment.class);
            } else if (name.equals("Proxy(with AOP)")) {
                readyGo(ProxyFragment.class);
            } else if (name.equals("SingleInstance")) {
                readyGo(SingleFragment.class);
            } else if (name.equals("Strategy")) {
                readyGo(StrategyFragment.class);
            } else if (name.equals("Builder")) {
                readyGo(BuilderFragment.class);
            } else if (name.equals("Annotation")) {
                readyGo(AnnotationFragment.class);
            } else if (name.equals("Observer")) {
                readyGo(ObserverFragment.class);
            } else if (name.equals("Immersion")) {
                readyGo(ImmersionActivity.class);
            } else if (name.equals("AOP")) {
                readyGo(AOPFragment.class);
            } else if (name.equals("Chain of Responsibility")) {
                readyGo(ResponsibilityChainFragment.class);
            } else if (name.equals("Template")) {
                readyGo(TemplateFragment.class);
            } else if (name.equals("Adapter")) {
                readyGo(AdapterFragment.class);
            }
        }
    }
}
