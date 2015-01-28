package com.ryan.ryanapp.ui.customeview;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;

import com.ryan.ryanapp.R;
import com.ryan.ryanapp.Utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class PullToRefreshRecyclerView<T> extends SwipeRefreshLayout implements SwipeRefreshLayout.OnRefreshListener {

    public static final int FOOTER_VIEW_TYPE = -1;
    protected static final String TAG = "PullToRefreshRecyclerView";
    private BaseRecyclerViewAdapter adapter;
    private List<T> datas = new ArrayList<T>();
    private boolean canLoadMore = true;
    private FooterView footerView;
    private TouchableRecyclerView recyclerView;

    public PullToRefreshRecyclerView(Context context) {

        super(context);
        addRecyclerView();
    }

    public PullToRefreshRecyclerView(Context context, AttributeSet attrs) {

        super(context, attrs);
        addRecyclerView();
    }


    private void addRecyclerView() {

        setColorSchemeResources(R.color.theme_color, R.color.red_text_color);
        setOnRefreshListener(this);
        recyclerView = (TouchableRecyclerView) View.inflate(getContext(), R.layout.recyclerview, this).findViewById(R.id.recyclerView);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new BaseRecyclerViewAdapter();
        footerView = new FooterView(getContext());
        footerView.setLoadingText("数据加载中");
        recyclerView.setAdapter(adapter);
        recyclerView.setOnScrollListener(new OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                if (datas.size() > 0) {
                    super.onScrolled(recyclerView, dx, dy);
                    int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                    int itemCount = layoutManager.getItemCount();
                    if (isRefreshing()) {
                        return;
                    }
                    LogUtils.i(TAG, lastVisibleItem + "  " + itemCount + "  " + canLoadMore);
                    if (lastVisibleItem + 1 >= itemCount) {
                        if (canLoadMore) {
                            footerView.setLoadingText("数据加载中");
                            footerView.setProgressBarVisibility(View.VISIBLE);
                            onLoadMore();
                        }
                    }
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }

    public List<T> getDatas() {

        return datas;
    }

    public void notifyDataSetChanged() {

        adapter.notifyDataSetChanged();
    }

    /**
     * 设置是否可以加载更多
     */
    public void setCanLoadMore(boolean canLoadMore) {

        this.canLoadMore = canLoadMore;
    }

    public boolean canLoadMore() {

        return canLoadMore;
    }

    public FooterView getFooterView() {

        return footerView;
    }

    @Override
    public void setRefreshing(boolean refreshing) {

        super.setRefreshing(refreshing);
//        recyclerView.setTouchable(!refreshing);
        if (!canLoadMore) {
            footerView.setLoadingText("没有更多数据");
            footerView.setProgressBarVisibility(View.GONE);
        }
    }

    public abstract void onBindBaseViewHolder(ViewHolder viewHolder, int position);

    public abstract ViewHolder onCreateBaseViewHolder(ViewGroup viewGroup, int itemType);

    public abstract int getBaseItemViewType(int position);

    public abstract void onLoadMore();

    public abstract void onRefreshing();

    @Override
    public void onRefresh() {

        recyclerView.setTouchable(false);
        onRefreshing();
    }

    class BaseRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @Override
        public int getItemCount() {

            return datas.size() + 1;
        }

        @Override
        public int getItemViewType(int position) {

            if (position == datas.size()) {
                return FOOTER_VIEW_TYPE;
            }
            return getBaseItemViewType(position);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {

            if (position == datas.size()) {
                return;
            }
            onBindBaseViewHolder(viewHolder, position);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int itemType) {

            if (itemType == FOOTER_VIEW_TYPE) {
                return new FooterViewHolder(footerView);
            }
            return onCreateBaseViewHolder(viewGroup, itemType);
        }
    }


}
