package com.ryan.ryanapp.ui;

import android.os.Bundle;
import android.view.ViewAnimationUtils;

import com.ryan.ryanapp.R;

/**
 * 作者： Ryan
 * 创建时间：2015年1月29日 16:49
 * 描述：我的主页
 */
public class ActivityMeHome extends ActivityBase {

    private static FragmentBase beingLoadedFragment;
    public static void setBeingLoadedFragment(FragmentBase beingLoadedFragment) {
        ActivityMeHome.beingLoadedFragment = beingLoadedFragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        switchFragment(beingLoadedFragment, R.id.baseViewContainer, false);
    }

    @Override protected void initView() {

    }

}
