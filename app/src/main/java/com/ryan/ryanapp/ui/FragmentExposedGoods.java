package com.ryan.ryanapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ryan.ryanapp.R;

import java.util.Iterator;
import java.util.Map;

/**
 * 作者： Ryan
 * 创建时间：2015年1月29日 16:49
 * 描述：已发布的商品
 */
public class FragmentExposedGoods extends FragmentBase {


    public static FragmentExposedGoods newInstance(Map<String, String> params) {
        FragmentExposedGoods fragmentExposedGoods = new FragmentExposedGoods();
        Bundle args = new Bundle();
        Iterator<String> iterator = params.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            args.putString(key, params.get(key));
        }
        fragmentExposedGoods.setArguments(args);
        return fragmentExposedGoods;
    }
    public FragmentExposedGoods() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exposed_goods, container, false);
    }

    @Override public void onResume() {
        super.onResume();
        toolbar.setTitle("我的商品");
    }
}
