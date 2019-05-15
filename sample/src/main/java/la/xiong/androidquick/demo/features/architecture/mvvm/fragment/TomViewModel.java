package la.xiong.androidquick.demo.features.architecture.mvvm.fragment;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.List;

import la.xiong.androidquick.demo.base.mvvm.BaseViewModel;
import la.xiong.androidquick.demo.bean.NameBean;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class TomViewModel extends BaseViewModel<TomRepository> {
    public TomViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected TomRepository getRepository() {
        return new TomRepository(context);
    }

    public LiveData<List<NameBean>> getData() {
        final MutableLiveData<List<NameBean>> liveData = repository.getData();
        return liveData;
    }

    public void loadData() {
        repository.getTomData();
    }
}
