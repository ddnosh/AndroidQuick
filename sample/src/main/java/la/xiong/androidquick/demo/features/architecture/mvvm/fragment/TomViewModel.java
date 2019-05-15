package la.xiong.androidquick.demo.features.architecture.mvvm.fragment;

import android.app.Application;
import android.arch.lifecycle.LiveData;
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

    public LiveData<List<NameBean>> getTomData() {
        return repository.getTomData();
    }
}
