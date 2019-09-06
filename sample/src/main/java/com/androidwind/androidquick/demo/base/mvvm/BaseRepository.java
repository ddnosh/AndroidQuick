package com.androidwind.androidquick.demo.base.mvvm;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

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
