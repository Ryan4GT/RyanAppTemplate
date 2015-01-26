package com.ryan.ryanapp.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.ryan.ryanapp.R;
import com.ryan.ryanapp.Utils.LogUtils;

import java.util.Map;


public abstract class ActivityBase extends ActionBarActivity implements OnMenuItemClickListener, View.OnClickListener, FragmentBase.OnFragmentInteractionListener {
    protected String TAG;
    protected Toolbar toolbar;
    protected FrameLayout baseViewContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();
        setContentView(R.layout.activity_base);
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        baseViewContainer = (FrameLayout) findViewById(R.id.baseViewContainer);
        initView();
        toolbar.setBackgroundResource(R.color.white);
        toolbar.setOnMenuItemClickListener(this);
        toolbar.setNavigationOnClickListener(this);
    }

    /**
     * 设置Toolbar和Activity界面视图
     */
    protected abstract void initView();


    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        LogUtils.i(TAG, "点击了： " + menuItem.getItemId());
        return false;
    }

    /**
     * 根据应用传递Fragment的关键字打开响应的页面
     * @param fragmentKey FragmentBase对应的数值
     * @return
     */
    private FragmentBase getBeingOpenedFragment(int fragmentKey) {

        FragmentBase beingOpenedFragment = null;
        switch (fragmentKey) {

        }
        return beingOpenedFragment;
    }

    /**
     * 切换Frament
     * @param fragmentKey    FragmentBase对应的key
     * @param addToBackStack 是否放入返回栈
     */
    protected void switchFragment(int fragmentKey, boolean addToBackStack) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        FragmentBase beingOpenedFragment = getBeingOpenedFragment(fragmentKey);
        if (beingOpenedFragment.isAdded()) {
            transaction.show(beingOpenedFragment);
        } else {
            transaction.setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out);
            if (addToBackStack) {
                transaction.addToBackStack(beingOpenedFragment.getClass().getSimpleName());
            }
            transaction.replace(R.id.baseViewContainer, beingOpenedFragment, beingOpenedFragment.getClass().getSimpleName());
        }

        transaction.commit();
    }

    /**
     * 移除Frament
     */
    protected void removeFragment(FragmentBase fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.remove(fragment);
        transaction.commit();
        fragmentManager.popBackStack();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == -1) {
            LogUtils.i(TAG, "点击了顶部导航返回键……");
        }
    }

    @Override
    public void onFragmentInteraction(Map<String, Object> args) {

    }
}
