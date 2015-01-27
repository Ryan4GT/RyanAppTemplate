package com.ryan.ryanapp.ui;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ryan.ryanapp.R;

import java.util.HashMap;

public class ActivitySplash extends ActivityBase {

    @Override protected void initView() {
        toolbar.setVisibility(View.GONE);
        switchFragment(FragmentSplash.newInstance(new HashMap<String, String>()), R.id.baseViewContainer, false);
    }

}
