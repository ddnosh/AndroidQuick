package com.androidwind.androidquick.demo.features.function.annotation.manager;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * <pre>
 *     author : lex
 *     e-mail : ldlywt@163.com
 *     time   : 2018/08/14
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class ViewManager {

    private Activity mActivity;
    private Fragment mFragment;
    private View mView;

    public ViewManager(Activity activity) {
        this.mActivity = activity;
    }

    public ViewManager(View view) {
        this.mView = view;
    }

    public ViewManager(Fragment fragment) {
        this.mFragment = fragment;
    }

    public View findViewById(int resId){
        View view = null;
        if (mActivity != null) {
            view = mActivity.findViewById(resId);
        }
        if (mFragment != null) {
            view = mFragment.getActivity().findViewById(resId);
        }
        if (mView != null) {
            view = mView.findViewById(resId);
        }
        return view;
    }

    /**
     * 设置根布局，仅限Activity
     *
     * @param resId
     */
    public void setContentView(int resId) {
        if (mActivity != null) {
            mActivity.setContentView(resId);
        }
    }

    /**
     * 获取颜色
     *
     * @param resId
     */
    public int getColor(int resId) {
        int color = -1;
        if (mActivity != null) {
            color = mActivity.getResources().getColor(resId);
        }
        if (mFragment != null) {
            color = mFragment.getActivity().getResources().getColor(resId);
        }
        return color;
    }


    /**
     * 获取字符串
     *
     * @param resId
     */
    public String getString(int resId) {
        String str = "";
        if (mActivity != null) {
            str = mActivity.getString(resId);
        }
        if (mFragment != null) {
            str = mFragment.getActivity().getString(resId);
        }
        return str;
    }
}
