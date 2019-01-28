package la.xiong.androidquick.demo.module.db.greendao;

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
import la.xiong.androidquick.demo.base.BaseTFragment;
import la.xiong.androidquick.ui.adapter.CommonAdapter;
import la.xiong.androidquick.ui.adapter.CommonViewHolder;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class GreenDaoFragment extends BaseTFragment {

    public static final String TAG = "GreenDaoFragment";

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
    protected void initViewsAndEvents() {
        mUserList = new ArrayList<User>();
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
        return R.layout.fragment_database_greendao;
    }

    @OnClick({R.id.btn_add, R.id.btn_delete, R.id.btn_update, R.id.btn_query})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                RealUserDao.getInstance().addUser(new User(null, mEditText.getText().toString(), 20));
                query();
                break;
            case R.id.btn_delete:
                RealUserDao.getInstance().deleteUser(mEditText.getText().toString());
                query();
                break;
            case R.id.btn_update:
                User user = RealUserDao.getInstance().queryUser(2);
                user.setName(mEditText.getText().toString());
                RealUserDao.getInstance().updateUser(user);
                mCommonAdapter.notifyDataSetChanged();
                break;
            case R.id.btn_query:
                query();
                break;
        }
    }

    private void query() {
        mUserList.clear();
        mUserList.addAll(RealUserDao.getInstance().queryAllUsers());
        mCommonAdapter.notifyDataSetChanged();
    }
}
