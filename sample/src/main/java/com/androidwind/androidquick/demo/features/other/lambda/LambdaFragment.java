package com.androidwind.androidquick.demo.features.other.lambda;

import android.os.Bundle;
import android.widget.Button;

import com.androidwind.androidquick.demo.R;
import com.androidwind.androidquick.demo.base.BaseFragment;
import com.androidwind.androidquick.util.ToastUtil;
import com.androidwind.annotation.annotation.BindTag;
import com.androidwind.annotation.annotation.TagInfo;

import io.reactivex.Observable;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@BindTag(type = TagInfo.Type.FRAGMENT, tags = {"lambda"}, description = "Lambda实例")
public class LambdaFragment extends BaseFragment {

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        Button btn = getActivity().findViewById(R.id.btn_lambda);
        btn.setOnClickListener(v -> {
            ToastUtil.showToast("lambda clicked!");
            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        });
        Observable.create(e -> {
            e.onNext("1");
            e.onNext("2");
            e.onComplete();
        }).subscribe();
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_other_lambda;
    }

}
