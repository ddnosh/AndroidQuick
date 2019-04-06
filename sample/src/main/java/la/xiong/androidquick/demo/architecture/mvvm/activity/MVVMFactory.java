package la.xiong.androidquick.demo.architecture.mvvm.activity;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class MVVMFactory extends ViewModelProvider.NewInstanceFactory {
    private LifecycleProvider<ActivityEvent> mActivityEventLifecycleProvider;
    private MVVMRepository mRepository;

    public MVVMFactory(MVVMRepository repository, LifecycleProvider<ActivityEvent> activityEventLifecycleProvider) {
        mActivityEventLifecycleProvider = activityEventLifecycleProvider;
        mRepository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new MVVMViewModel(mRepository, mActivityEventLifecycleProvider);
    }
}
