package com.androidwind.androidquick.demo.base.mvvm;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class BaseRepository<T> {

    protected Context context;
    protected final MutableLiveData<T> liveData;

    public BaseRepository(Context context) {
        this.context = context;
        liveData = new MutableLiveData<>();
    }

    public MutableLiveData<T> getData() {
        return liveData;
    }
}
