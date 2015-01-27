package com.ryan.ryanapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ryan.ryanapp.R;

import java.util.Iterator;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSplash extends FragmentBase {

    /**
     * Use this factory method to create a new instance of this fragment using the provided parameters.
     *
     * @return A new instance of fragment FragmentBase.
     */
    public static FragmentSplash newInstance(Map<String, String> params) {
        FragmentSplash fragmentSplash = new FragmentSplash();
        Bundle args = new Bundle();
        Iterator<String> iterator = params.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            args.putString(key, params.get(key));
        }
        fragmentSplash.setArguments(args);
        return fragmentSplash;
    }

    public FragmentSplash() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override public void onResume() {
        super.onResume();
        toolbar.setVisibility(View.GONE);
        baseHandler.sendEmptyMessageDelayed(0, 2000);
    }

    @Override public boolean handleMessage(Message msg) {
        startActivity(new Intent(getActivity(), ActivityMain.class));
        getActivity().finish();
        return super.handleMessage(msg);

    }
}


