package la.xiong.androidquick.demo.ui.fragment;

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
import la.xiong.androidquick.demo.db.DBManager;
import la.xiong.androidquick.demo.db.User;
import la.xiong.androidquick.ui.adapter.CommonAdapter;
import la.xiong.androidquick.ui.adapter.CommonViewHolder;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class GreenDaoFragment extends BaseTFragment {

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
        return R.layout.fragment_greendao;
    }

    @OnClick({R.id.btn_add, R.id.btn_delete, R.id.btn_update, R.id.btn_query})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                DBManager.getInstance().addUser(new User(null, mEditText.getText().toString(), 20));
                break;
            case R.id.btn_delete:
                DBManager.getInstance().deleteUser(mEditText.getText().toString());
                mCommonAdapter.notifyDataSetChanged();
                break;
            case R.id.btn_update:
                User user = DBManager.getInstance().queryUser(2);
                user.setName(mEditText.getText().toString());
                DBManager.getInstance().updateUser(user);
                mCommonAdapter.notifyDataSetChanged();
                break;
            case R.id.btn_query:
                mUserList.clear();
                mUserList.addAll(DBManager.getInstance().queryAllUsers());
                mCommonAdapter.notifyDataSetChanged();
                break;
        }
    }
}
