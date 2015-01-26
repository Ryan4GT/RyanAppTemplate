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
public class FragmentOrder extends FragmentBase {

    /**
     * Use this factory method to create a new instance of this fragment using the provided parameters.
     * @return A new instance of fragment FragmentBase.
     */
    public static FragmentOrder newInstance(Map<String, String> params) {
        FragmentOrder fragmentOrder = new FragmentOrder();
        Bundle args = new Bundle();
        Iterator<String> iterator = params.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            args.putString(key, params.get(key));
        }
        fragmentOrder.setArguments(args);
        return fragmentOrder;
    }

    public FragmentOrder() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return fragmentRootView  = inflater.inflate(R.layout.fragment_order, container, false);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        toolbar.setTitle("FragmentOrder");
        toolbar.getMenu().clear();
    }
}
