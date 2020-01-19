package com.androidwind.androidquick.demo.features.architecture.mvvm.activity;

import com.androidwind.androidquick.demo.bean.NameBean;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class MVVMViewModel2 extends ViewModel {

    private MVVMRepository2 repository;

    public MVVMViewModel2(MVVMRepository2 repository) {
        this.repository = repository;
    }

    public MutableLiveData<List<NameBean>> getTestData() {
        return repository.getTestData();
    }
}
