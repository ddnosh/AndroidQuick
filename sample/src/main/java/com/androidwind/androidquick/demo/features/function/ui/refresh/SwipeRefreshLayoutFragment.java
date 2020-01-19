package com.androidwind.androidquick.demo.features.function.ui.refresh;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.androidwind.androidquick.demo.R;
import com.androidwind.androidquick.demo.base.BaseFragment;
import com.androidwind.annotation.annotation.BindTag;
import com.androidwind.annotation.annotation.TagInfo;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@BindTag(type = TagInfo.Type.FRAGMENT, tags = {"refresh", "下拉刷新"}, description = "Fragment + SwipeRefreshLayout实例")
public class SwipeRefreshLayoutFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private static int getRandom(int count) {
        return (int) Math.round(Math.random() * (count));
    }

    private static String string = "abcdefghijklmnopqrstuvwxyz";

    private Handler handler = new Handler();

    public static String getRandomString(int length) {
        StringBuffer sb = new StringBuffer();
        int len = string.length();
        for (int i = 0; i < length; i++) {
            sb.append(string.charAt(getRandom(len - 1)));
        }
        return sb.toString();
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        recyclerView = getActivity().findViewById(R.id.recyclerView);
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            datas.add(getRandomString(5));
        }
        final RecyclerviewAdapter adapter = new RecyclerviewAdapter(getContext(), datas);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        //pull down
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.setData(getRandomString(6));
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        recyclerView.addOnScrollListener(new onLoadMoreListener() {
            @Override
            protected void onLoading(int countItem, int lastItem) {
                showLoadingDialog();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dismissLoadingDialog();
                        adapter.setData(getRandomString(7));
                    }
                }, 2000);
            }
        });
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_function_ui_refresh_swiperefreshlayout;
    }

    public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder> {

        private Context context;
        private List<String> data;

        public RecyclerviewAdapter(Context context, List<String> data) {
            this.context = context;
            this.data = data;
        }

        public void setData(List<String> data) {
            this.data = data;
            notifyDataSetChanged();
        }

        public void setData(String data) {
            this.data.add(data);
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.activity_function_ui_refresh_smartrefreshlayout_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
            holder.name.setText(data.get(position));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "" + position, Toast.LENGTH_SHORT).show();
                }
            });

        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            private TextView name;

            public ViewHolder(View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.name);

            }
        }
    }

    abstract class onLoadMoreListener extends RecyclerView.OnScrollListener {
        private int countItem;
        private int lastItem;
        private boolean isScolled = false;
        private RecyclerView.LayoutManager layoutManager;

        protected abstract void onLoading(int countItem, int lastItem);

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            if (newState == recyclerView.SCROLL_STATE_DRAGGING || newState == recyclerView.SCROLL_STATE_SETTLING) {
                isScolled = true;
            } else {
                isScolled = false;
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                layoutManager = recyclerView.getLayoutManager();
                countItem = layoutManager.getItemCount();
                lastItem = ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
            }
            if (isScolled && countItem != lastItem && lastItem == countItem - 1) {
                onLoading(countItem, lastItem);
            }
        }
    }
}
