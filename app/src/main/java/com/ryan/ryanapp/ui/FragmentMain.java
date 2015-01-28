package com.ryan.ryanapp.ui;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.CountCallback;
import com.avos.avoscloud.FindCallback;
import com.ryan.ryanapp.R;
import com.ryan.ryanapp.Utils.LogUtils;
import com.ryan.ryanapp.leancloud.bean.User;

import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class FragmentMain extends FragmentBase {

    /**
     * Use this factory method to create a new instance of this fragment using the provided parameters.
     * @return A new instance of fragment FragmentBase.
     */
    public static FragmentMain newInstance(Map<String, String> params) {
        FragmentMain fragmentMain = new FragmentMain();
        Bundle args = new Bundle();
        Iterator<String> iterator = params.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            args.putString(key, params.get(key));
        }
        fragmentMain.setArguments(args);
        return fragmentMain;
    }

    public FragmentMain() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {

        }
    }

    @Override public void onAttach(Activity activity) {
        super.onAttach(activity);
        toolbar.setTitle(R.string.fragment_main_title);
        AVUser.getQuery().countInBackground(new CountCallback() {
            @Override public void done(int i, AVException e) {
                LogUtils.i(TAG, "共有" + i + "个用户");
                AVUser.getQuery(User.class).findInBackground(new FindCallback<User>() {
                    @Override public void done(List<User> users, AVException e) {
                        LogUtils.i(TAG, "查询到得用户有： " + users);
                    }
                });
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return fragmentRootView = inflater.inflate(R.layout.fragment_main, container, false);
    }


}
