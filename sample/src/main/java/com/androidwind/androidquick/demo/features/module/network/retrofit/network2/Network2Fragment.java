package com.androidwind.androidquick.demo.features.module.network.retrofit.network2;

import android.os.Bundle;
import android.view.View;

import com.androidwind.androidquick.demo.R;
import com.androidwind.androidquick.demo.base.BaseFragment;
import com.androidwind.androidquick.demo.bean.NameBean;
import com.androidwind.androidquick.demo.bean.TestBean;
import com.androidwind.androidquick.demo.constant.Constants;
import com.androidwind.androidquick.demo.features.module.network.retrofit.RetrofitManager;
import com.androidwind.androidquick.demo.features.module.network.retrofit.TestApis;
import com.androidwind.androidquick.module.retrofit.exeception.ApiException;
import com.androidwind.androidquick.module.rxjava.BaseObserver;
import com.androidwind.androidquick.ui.adapter.CommonAdapter;
import com.androidwind.androidquick.ui.adapter.CommonViewHolder;
import com.androidwind.androidquick.util.LogUtil;
import com.androidwind.androidquick.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * (Retrofit+OkHttp+RxJava)Different Url
 *
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class Network2Fragment extends BaseFragment {

    protected static String TAG = "NetworkActivity";

    @BindView(R.id.rv_adapter)
    RecyclerView mRecyclerView;

    private CommonAdapter mCommonAdapter;

    private List<TestBean> mTestBeanList;
//    private CompositeSubscription mCompositeSubscription;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_network2;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        showLoadingDialog("加载中...");

        mTestBeanList = new ArrayList<>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mCommonAdapter = new CommonAdapter<TestBean>(getActivity(), R.layout.item_common_adapter_2, mTestBeanList) {
            @Override
            public void convert(CommonViewHolder holder, final TestBean bean) {
                holder.setText(R.id.tv_login, bean.getLogin());
                holder.setText(R.id.tv_id, bean.getId() + "");
                holder.setImageResourceWithGlide(R.id.iv_avatar, bean.getAvatar_url());
                holder.setOnClickListener(R.id.ll_rv_common_adapter_item, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LogUtil.d(TAG, "onItemClick");
                        ToastUtil.showToast(bean.getLogin() + " clicked!");
                        bean.setLogin(bean.getLogin() + " clicked!");
                        notifyDataSetChanged();
                    }
                });
            }
        };
        mRecyclerView.setAdapter(mCommonAdapter);

//        mCompositeSubscription = new CompositeSubscription();
//        Subscription subscription =
        RetrofitManager.INSTANCE.getRetrofit(Constants.GANK_API_URL).create(TestApis.class)
                // .getOctocat("https://api.github.com/repos/octocat/Hello-World/contributors")
                .getOctocat()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<TestBean>>() {

                               @Override
                               public void onError(ApiException exception) {
                                   LogUtil.e(TAG, "error:" + exception.getMessage());
                               }

                               @Override
                               public void onSuccess(List<TestBean> testBeans) {
                                   dismissLoadingDialog();
                                   LogUtil.i(TAG, testBeans.toString());
                                   //不能这样赋值:mTestBeanList = list;
                                   //方法一
//                        mTestBeanList.clear();
//                        mTestBeanList.addAll(list);
//                        mCommonAdapter.notifyDataSetChanged();
                                   //方法二
                                   mCommonAdapter.update(testBeans);
                               }
                           }
                );
//        mCompositeSubscription.add(subscription);

        RetrofitManager.INSTANCE.getRetrofit(Constants.GANK_API_URL).create(TestApis.class)
                .getTestData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<NameBean>>() {

                               @Override
                               public void onError(ApiException exception) {
                                   LogUtil.e(TAG, "error:" + exception.getMessage());
                               }

                               @Override
                               public void onSuccess(List<NameBean> testBeans) {
                                   dismissLoadingDialog();
                                   LogUtil.i(TAG, testBeans.toString());
                                   ToastUtil.showToast(testBeans.toString());
                               }
                           }
                );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        if (null != mCompositeSubscription) {
//            mCompositeSubscription.unsubscribe();
//        }
    }
}
