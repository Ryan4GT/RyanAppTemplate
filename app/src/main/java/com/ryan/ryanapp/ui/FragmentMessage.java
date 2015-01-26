package com.ryan.ryanapp.ui;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ryan.ryanapp.R;
import com.ryan.ryanapp.Utils.LogUtils;

import java.util.Iterator;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMessage extends FragmentBase {

    /**
     * Use this factory method to create a new instance of this fragment using the provided parameters.
     * @return A new instance of fragment FragmentBase.
     */
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
        // Inflate the layout for this fragment
        return fragmentRootView = inflater.inflate(R.layout.fragment_message, container, false);
    }




}
