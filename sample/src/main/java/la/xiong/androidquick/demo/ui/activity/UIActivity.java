package la.xiong.androidquick.demo.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseActivity;
import la.xiong.androidquick.demo.ui.fragment.BannerFragment;
import la.xiong.androidquick.demo.ui.fragment.BaseRecyclerViewAdapterHelperFragment;
import la.xiong.androidquick.demo.ui.fragment.BottomBarFragment;
import la.xiong.androidquick.demo.ui.fragment.CodeFragment;
import la.xiong.androidquick.demo.ui.fragment.CommonAdapterFragment;
import la.xiong.androidquick.demo.ui.fragment.CommonToolBarFragment;
import la.xiong.androidquick.demo.ui.fragment.DatabindingFragment;
import la.xiong.androidquick.demo.ui.fragment.Example1Fragment;
import la.xiong.androidquick.demo.ui.fragment.MultiViewTypeAdapterFragment;
import la.xiong.androidquick.demo.ui.fragment.PageStatusFragment;
import la.xiong.androidquick.demo.ui.fragment.RadioButtonFragment;
import la.xiong.androidquick.demo.ui.fragment.TabFTLFragment;
import la.xiong.androidquick.demo.ui.fragment.TabSTLFragment;
import la.xiong.androidquick.demo.view.expandablelistview.AnimatedExpandableListView;
import la.xiong.androidquick.demo.view.expandablelistview.AnimatedListAdapter;
import la.xiong.androidquick.demo.view.expandablelistview.GroupItem;
import la.xiong.androidquick.tool.DialogUtil;
import la.xiong.androidquick.tool.ToastUtil;
import la.xiong.androidquick.ui.dialog.CommonDialog;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@Deprecated
public class UIActivity extends BaseActivity {

    private static String TAG = "UIActivity";

    @BindView(R.id.explv_ui)
    AnimatedExpandableListView mAnimatedExpandableListView;
    private List<GroupItem> parentList = new ArrayList<>();

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_ui;
    }

    @Override
    protected void initViewsAndEvents() {

        GroupItem groupItem0 = new GroupItem();
        groupItem0.title = "Fragment";
        groupItem0.childList.add("CommonFragment");
        groupItem0.childList.add("Fragmentation");
        parentList.add(groupItem0);

        GroupItem groupItem1 = new GroupItem();
        groupItem1.title = "Adapter";
        groupItem1.childList.add("CommonAdapter");
        groupItem1.childList.add("MultiViewTypeAdapter");
        groupItem1.childList.add("BaseRecycleViewAdapterHelper");
        parentList.add(groupItem1);

        GroupItem groupItem2 = new GroupItem();
        groupItem2.title = "Toolbar";
        groupItem2.childList.add("DefaultToolbar");
        groupItem2.childList.add("CommonToolbar");
        parentList.add(groupItem2);

        GroupItem groupItem3 = new GroupItem();
        groupItem3.title = "BottomBar";
        groupItem3.childList.add("RadioButton");
        groupItem3.childList.add("BottomBar");
        parentList.add(groupItem3);

        GroupItem groupItem4 = new GroupItem();
        groupItem4.title = "Dialog";
        groupItem4.childList.add("LoadingDialog");
        groupItem4.childList.add("CommonDialog");
        parentList.add(groupItem4);

        GroupItem groupItem5 = new GroupItem();
        groupItem5.title = "Tab";
        groupItem5.childList.add("SmartTabLayout");
        groupItem5.childList.add("FlycoTabLayout");
        parentList.add(groupItem5);

        GroupItem groupItem6 = new GroupItem();
        groupItem6.title = "Banner";
        parentList.add(groupItem6);

        GroupItem groupItem7 = new GroupItem();
        groupItem7.title = "Code";
        parentList.add(groupItem7);

        GroupItem groupItem8 = new GroupItem();
        groupItem8.title = "Permission";
        parentList.add(groupItem8);

        GroupItem groupItem9 = new GroupItem();
        groupItem9.title = "Refresh";
        parentList.add(groupItem9);

        GroupItem groupItem10 = new GroupItem();
        groupItem10.title = "WebView";
        parentList.add(groupItem10);

        GroupItem groupItem11 = new GroupItem();
        groupItem11.title = "DataBinding";
        parentList.add(groupItem11);

        GroupItem groupItem12 = new GroupItem();
        groupItem12.title = "PageStatus";
        parentList.add(groupItem12);

        mAnimatedExpandableListView.setAdapter(new AnimatedListAdapter(this, parentList));

        //默认第一组打开
//        mAnimatedExpandableListView.expandGroupWithAnimation(0);

        //点击分组打开或关闭时添加动画
        mAnimatedExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Log.i(TAG, "group" + groupPosition + " was clicked!");
                if (parentList.get(groupPosition).childList.size() > 0) {
                    if (mAnimatedExpandableListView.isGroupExpanded(groupPosition)) {
                        mAnimatedExpandableListView.collapseGroupWithAnimation(groupPosition);
                    } else {
                        mAnimatedExpandableListView.expandGroupWithAnimation(groupPosition);
                    }
                }
                switch (groupPosition) {
                    case 6:
                        readyGo(BannerFragment.class);
                        break;
                    case 7:
                        readyGo(CodeFragment.class);
                        break;
                    case 8:
                        readyGo(PermissionActivity.class);
                        break;
                    case 9:
                        readyGo(Example1Fragment.class);
                        break;
                    case 10:
                        Bundle bundle = new Bundle();
                        bundle.putString("url", "https://github.com/ddnosh");
                        readyGo(WebViewActivity.class, bundle);
                        break;
                    case 11:
                        readyGo(DatabindingFragment.class);
                        break;
                    case 12:
                        readyGo(PageStatusFragment.class);
                        break;
                }

                return true;
            }
        });

        mAnimatedExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
                Log.i(TAG, "group" + groupPosition + " with child" + childPosition + " was clicked!");
                switch (groupPosition) {
                    case 0:
                        switch (childPosition)
                        {
                            case 0:
                                readyGo(Example1Fragment.class);
                                break;
                            case 1:
                                readyGo(FragmentationActivity.class);
                                break;
                        }
                        break;
                    case 1:
                        switch (childPosition)
                        {
                            case 0:
                                readyGo(CommonAdapterFragment.class);
                                break;
                            case 1:
                                readyGo(MultiViewTypeAdapterFragment.class);
                                break;
                            case 2:
                                readyGo(BaseRecyclerViewAdapterHelperFragment.class);
                                break;
                        }
                        break;
                    case 2:
                        switch (childPosition)
                        {
                            case 0:
                                readyGo(ToolbarActivity.class);
                                break;
                            case 1:
                                readyGo(CommonToolBarFragment.class);
                                break;
                        }
                        break;
                    case 3:
                        switch (childPosition)
                        {
                            case 0:
                                readyGo(RadioButtonFragment.class);
                                break;
                            case 1:
                                readyGo(BottomBarFragment.class);
                                break;
                        }
                        break;
                    case 4:
                        switch (childPosition)
                        {
                            case 0:
                                DialogUtil.showLoadingDialog(UIActivity.this);
                                break;
                            case 1:
                                DialogUtil.getDialogBuilder(UIActivity.this)
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
                                break;
                        }
                        break;
                    case 5:
                        switch (childPosition) {
                            case 0:
                                readyGo(TabSTLFragment.class);
                                break;
                            case 1:
                                readyGo(TabFTLFragment.class);
                                break;
                        }
                        break;
                }
                return true;
            }
        });
    }

//    @OnClick({R.id.btn_ui_fragment, R.id.btn_ui_adapter, R.id.btn_ui_dialog,
//            R.id.btn_ui_databinding, R.id.btn_ui_toolbar, R.id.btn_ui_tab,
//            R.id.btn_ui_code, R.id.btn_ui_permission, R.id.btn_ui_refresh,
//            R.id.btn_ui_webview, R.id.btn_ui_go})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.btn_ui_fragment:
//                readyGo(Example1Fragment.class);
//                break;
//            case R.id.btn_ui_adapter:
//                readyGo(AdapterActivity.class);
//                break;
//            case R.id.btn_ui_dialog:
//                readyGo(DialogActivity.class);
//                break;
//            case R.id.btn_ui_databinding:
//                readyGo(DatabindingFragment.class);
//                break;
//            case R.id.btn_ui_toolbar:
//                readyGo(ToolbarActivity.class);
//                break;
//            case R.id.btn_ui_tab:
//                readyGo(TabActivity.class);
//                break;
//            case R.id.btn_ui_code:
//                readyGo(CodeFragment.class);
//                break;
//            case R.id.btn_ui_permission:
//                readyGo(PermissionActivity.class);
//                break;
//            case R.id.btn_ui_refresh:
//                readyGo(Example1Fragment.class);
//                break;
//            case R.id.btn_ui_webview:
//                readyGo(WebViewActivity.class);
//                break;
//            case R.id.btn_ui_go:
//                readyGo(HostActivity.class);
//                break;
//        }
//    }
}
