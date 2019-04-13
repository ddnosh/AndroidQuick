package la.xiong.androidquick.demo.features.module.db.ormlite;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseFragment;
import la.xiong.androidquick.ui.adapter.CommonAdapter;
import la.xiong.androidquick.ui.adapter.CommonViewHolder;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class OrmLiteFragment extends BaseFragment {

    public static final String TAG = "OrmLiteFragment";

    @BindView(R.id.et_greendao_input)
    EditText mEditText;

    @BindView(R.id.btn_add)
    Button mBtnAdd;
    @BindView(R.id.btn_delete)
    Button mBtnDelete;
    @BindView(R.id.btn_update)
    Button mBtnUpdate;
    @BindView(R.id.btn_query)
    Button mBtnQuery;

    @BindView(R.id.rv_greendao)
    RecyclerView mRecyclerView;

    private CommonAdapter mCommonAdapter;

    private List<User> mUserList;

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        mUserList = new ArrayList<>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mCommonAdapter = new CommonAdapter<User>(getActivity(), R.layout.item_common_adapter_1, mUserList) {
            @Override
            public void convert(CommonViewHolder holder, final User user) {
                holder.setText(R.id.tv_name, user.getName());
            }
        };
        mRecyclerView.setAdapter(mCommonAdapter);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_database_ormlite;
    }

    @OnClick({R.id.btn_add, R.id.btn_delete, R.id.btn_update, R.id.btn_query})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                UserDao.getInstance().createUser(mEditText.getText().toString());
                query();
                break;
            case R.id.btn_delete:
                UserDao.getInstance().delete();
                query();
                break;
            case R.id.btn_update:
                User user = UserDao.getInstance().getUserById(1);
                user.setName(mEditText.getText().toString());
                UserDao.getInstance().update(user);
                query();
                break;
            case R.id.btn_query:
                query();
                break;
        }
    }

    private void query() {
        mUserList.clear();
        mUserList.addAll(UserDao.getInstance().getAllUser());
        mCommonAdapter.notifyDataSetChanged();
    }
}
