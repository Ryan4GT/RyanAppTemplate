package com.ryan.ryanapp.ui;


import android.app.Activity;
import android.os.Bundle;
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
public class FragmentMe extends FragmentBase {


    /**
     * Use this factory method to create a new instance of this fragment using the provided parameters.
     * @return A new instance of fragment FragmentBase.
     */
    public static FragmentMe newInstance(Map<String, String> params) {
        FragmentMe fragmentMe = new FragmentMe();
        Bundle args = new Bundle();
        Iterator<String> iterator = params.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            args.putString(key, params.get(key));
        }
        fragmentMe.setArguments(args);
        return fragmentMe;
    }

    public FragmentMe() {

    }

    @Override public void onAttach(Activity activity) {
        super.onAttach(activity);
        toolbar.setTitle(R.string.fragment_me_title);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return fragmentRootView = inflater.inflate(R.layout.fragment_me, container, false);
    }


    @Override
    public void onResume() {
        super.onResume();
    }
}
