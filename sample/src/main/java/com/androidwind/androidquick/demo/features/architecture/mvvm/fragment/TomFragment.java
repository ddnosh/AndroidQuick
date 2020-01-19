package com.androidwind.androidquick.demo.features.architecture.mvvm.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.androidwind.androidquick.demo.R;
import com.androidwind.androidquick.demo.base.mvvm.BaseMVVMFragment;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.OnClick;


/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class TomFragment extends BaseMVVMFragment<TomViewModel> {

    @BindView(R.id.tv_fragment_mvvm)
    TextView tvMVVM;

    @Override
    protected TomViewModel getViewModel() {
        return ViewModelProviders.of(getActivity()).get(TomViewModel.class);
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        viewModel.getData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                tvMVVM.setText(s);
            }
        });
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_architecture_mvvm;
    }

    @OnClick(R.id.btn_fragment_mvvm)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_fragment_mvvm:
                viewModel.loadData();
                break;
        }
    }
}
