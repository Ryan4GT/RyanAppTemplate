package com.ryan.ryanapp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ryan.ryanapp.R;

import java.util.Iterator;
import java.util.Map;

/**
 * 作者： Ryan
 * 创建时间：2015年1月29日 16:48
 * 描述：发布商品
 */
public class FragmentExposeGoods extends FragmentBase {


    public static FragmentExposeGoods newInstance(Map<String, String> params) {
        FragmentExposeGoods fragmentExposeGoods = new FragmentExposeGoods();
        Bundle args = new Bundle();
        Iterator<String> iterator = params.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            args.putString(key, params.get(key));
        }
        fragmentExposeGoods.setArguments(args);
        return fragmentExposeGoods;
    }

    public FragmentExposeGoods() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_expose_goods, container, false);
    }

    @Override public void onResume() {
        super.onResume();
        toolbar.setTitle("发布商品");
    }
}
