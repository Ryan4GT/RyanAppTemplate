package com.ryan.ryanapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.ryan.ryanapp.R;
import com.ryan.ryanapp.Utils.StringUtil;
import com.ryan.ryanapp.leancloud.UniversualImageLoaderUtils;
import com.ryan.ryanapp.leancloud.bean.User;
import com.ryan.ryanapp.ui.customeview.PullToRefreshRecyclerView;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FragmentMessage extends FragmentBase {

    private final int HANDLER_GET_DATA = 1;
    private final int HANDLER_REFRESH = 2;
    private PullToRefreshRecyclerView<User> refreshRecyclerView;
    private List<User> msgs;
    private boolean isLoading;
    private int pageIndex = 1;

    public static FragmentMessage newInstance(Map<String, String> params) {
        FragmentMessage fragmentMessage = new FragmentMessage();
        Bundle args = new Bundle();
        Iterator<String> iterator = params.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            args.putString(key, params.get(key));
        }
        fragmentMessage.setArguments(args);
        return fragmentMessage;
    }
    public FragmentMessage() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (fragmentRootView = inflater.inflate(R.layout.fragment_message, container, false));
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        toolbar.setTitle(R.string.fragment_message_title);
        LinearLayout recyclerViewContainer = (LinearLayout) fragmentRootView.findViewById(R.id.recyclerViewContainer);
        refreshRecyclerView = new PullToRefreshRecyclerView<User>(getActivity()) {
            private User user;
            @Override public void onBindBaseViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
                if(viewHolder instanceof FriendsViewHolder) {
                    FriendsViewHolder friendsViewHolder = (FriendsViewHolder) viewHolder;
                    user = msgs.get(position);
                    if(!StringUtil.isEmpty(user.getHeadImage())) {
                        ImageLoader.getInstance().displayImage(user.getHeadImage(), friendsViewHolder.headImageView, UniversualImageLoaderUtils.getDisplayImageOptions());
                    }
                    friendsViewHolder.nicknameView.setText(user.getUsername());
                }
            }
            @Override public RecyclerView.ViewHolder onCreateBaseViewHolder(ViewGroup viewGroup, int itemType) {
                View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_message_item, viewGroup, false);
                return new FriendsViewHolder(itemView);
            }
            @Override public int getBaseItemViewType(int position) {
                return 0;
            }
            @Override public void onLoadMore() {
                queryFriends();
            }
            @Override public void onRefreshing() {
                pageIndex = 1;
                queryFriends();
            }
        };
        recyclerViewContainer.addView(refreshRecyclerView);
        msgs = refreshRecyclerView.getDatas();
        queryFriends();
    }

    private void queryFriends() {
        if(!isLoading) {
            isLoading = true;
            AVQuery<User> userAVQuery = AVUser.getQuery(User.class);
            userAVQuery.setLimit(10);
            userAVQuery.setSkip((pageIndex - 1) * 10);
            userAVQuery.findInBackground(new FindCallback<User>() {
                @Override public void done(List<User> users, AVException e) {
                    if(e == null) {
                        if(users == null || users.size() < 10) {
                            refreshRecyclerView.setCanLoadMore(false);
                        }
                        if(pageIndex == 1) {
                            msgs.clear();
                        }
                        msgs.addAll(users);
                        refreshRecyclerView.notifyDataSetChanged();
                        refreshRecyclerView.setRefreshing(false);
                        pageIndex++;
                    } else {
                        refreshRecyclerView.setRefreshing(false);
                    }
                    isLoading = false;
                }
            });
        }

    }

    public class FriendsViewHolder extends RecyclerView.ViewHolder {
        public ImageView headImageView;
        public TextView nicknameView;
        public FriendsViewHolder(View itemView) {
            super(itemView);
            headImageView = (ImageView) itemView.findViewById(R.id.headImageView);
            nicknameView = (TextView) itemView.findViewById(R.id.nicknameView);
        }
    }

}
