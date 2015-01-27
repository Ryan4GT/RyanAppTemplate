package com.ryan.ryanapp.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

public abstract class ActivityBase extends ActionBarActivity implements OnMenuItemClickListener, View.OnClickListener, FragmentBase.OnFragmentInteractionListener, Handler.Callback {
    protected String TAG;
    protected Toolbar toolbar;
    protected FrameLayout baseViewContainer;
    protected Handler baseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();
        baseHandler = new Handler(this);
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
     * 切换Frament
     * @param addToBackStack 是否放入返回栈
     */
    protected void switchFragment(FragmentBase beingOpenedFragment, int container, boolean addToBackStack) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out);
        if (addToBackStack) {
            transaction.addToBackStack(beingOpenedFragment.getClass().getSimpleName());
        }
        transaction.replace(container, beingOpenedFragment, beingOpenedFragment.getClass().getSimpleName());
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

    @Override public boolean handleMessage(Message msg) {
        return false;
    }
}
