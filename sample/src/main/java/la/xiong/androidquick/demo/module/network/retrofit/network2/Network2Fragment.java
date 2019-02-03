package la.xiong.androidquick.demo.module.network.retrofit.network2;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import la.xiong.androidquick.demo.MyApplication;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseFragment;
import la.xiong.androidquick.demo.bean.TestBean;
import la.xiong.androidquick.demo.module.network.retrofit.TestApis;
import la.xiong.androidquick.demo.module.network.retrofit.base.BaseObserver;
import la.xiong.androidquick.network.retrofit.RetrofitManager;
import la.xiong.androidquick.tool.LogUtil;
import la.xiong.androidquick.tool.ToastUtil;
import la.xiong.androidquick.ui.adapter.CommonAdapter;
import la.xiong.androidquick.ui.adapter.CommonViewHolder;

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

    private RetrofitManager mRetrofitManager;
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
        mRetrofitManager = new RetrofitManager();
//        Subscription subscription =
        mRetrofitManager.createApi(MyApplication.getInstance().getApplicationContext(), TestApis.class)
                .getOctocat("https://api.github.com/repos/octocat/Hello-World/contributors")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<TestBean>>() {
                               @Override
                               public void onNext(List<TestBean> list) {
                                   dismissLoadingDialog();
                                   LogUtil.i(TAG, list.toString());
                                   //不能这样赋值:mTestBeanList = list;
                                   //方法一
//                        mTestBeanList.clear();
//                        mTestBeanList.addAll(list);
//                        mCommonAdapter.notifyDataSetChanged();
                                   //方法二
                                   mCommonAdapter.update(list);
                               }
                           }
                );
//        mCompositeSubscription.add(subscription);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        if (null != mCompositeSubscription) {
//            mCompositeSubscription.unsubscribe();
//        }
    }
}
