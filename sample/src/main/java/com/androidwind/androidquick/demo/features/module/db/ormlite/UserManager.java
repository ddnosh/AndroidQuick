package com.androidwind.androidquick.demo.features.module.db.ormlite;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class UserManager {

    private static UserManager sINSTANCE;

    public static UserManager getInstance() {
        if (sINSTANCE == null) {
            synchronized (UserManager.class) {
                if (sINSTANCE == null) {
                    sINSTANCE = new UserManager();
                }
            }
        }
        return sINSTANCE;
    }

    public void searchAndInsert() {
        Observable.create((ObservableOnSubscribe<String>) emitter -> emitter.onNext("Start"))
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        if ("Start".equals(s)) {
                            UserDao.getInstance().createUser("Mike");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
