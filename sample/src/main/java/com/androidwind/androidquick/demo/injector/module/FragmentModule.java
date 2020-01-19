package com.androidwind.androidquick.demo.injector.module;

import android.app.Activity;

import com.androidwind.androidquick.demo.injector.FragmentScope;

import androidx.fragment.app.Fragment;
import dagger.Module;
import dagger.Provides;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@Module
public class FragmentModule {
    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity provideActivity() {
        return fragment.getActivity();
    }
}
