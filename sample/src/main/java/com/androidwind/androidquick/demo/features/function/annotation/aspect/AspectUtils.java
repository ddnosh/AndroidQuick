package com.androidwind.androidquick.demo.features.function.annotation.aspect;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import androidx.fragment.app.Fragment;

public class AspectUtils {

    /**
     * 通过对象获取上下文
     */
    public static Context getContext(Object object) {
        if (object instanceof Activity) {
            return (Activity) object;
        } else if (object instanceof Fragment) {
            Fragment fragment = (Fragment) object;
            return fragment.getActivity();
        } else if (object instanceof android.app.Fragment) {
            android.app.Fragment fragment = (android.app.Fragment) object;
            return fragment.getActivity();
        } else if (object instanceof View) {
            View view = (View) object;
            return view.getContext();
        }
        return null;
    }
}
