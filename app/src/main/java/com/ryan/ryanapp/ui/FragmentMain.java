package com.ryan.ryanapp.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ryan.ryanapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMain extends FragmentBase {

    public FragmentMain() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if(arguments != null){

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentRootView = inflater.inflate(R.layout.fragment_main, container, false);
        return fragmentRootView;
    }



}
