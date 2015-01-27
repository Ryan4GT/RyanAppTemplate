package com.ryan.ryanapp.ui;

import android.os.Bundle;

import com.ryan.ryanapp.R;

import java.util.HashMap;

public class ActivityLogin extends ActivityBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override protected void initView() {
        switchFragment(FragmentLogin.newInstance(new HashMap<String, String>()), R.id.baseViewContainer, false);
    }

}



