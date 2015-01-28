package com.ryan.ryanapp.ui;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import com.ryan.ryanapp.R;
import com.ryan.ryanapp.ui.customeview.CooperativeScrollGestureListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOrder extends FragmentBase {

    private  ScrollView mSomeScroller;
    private static CooperativeScrollGestureListener mCoopScrollHandler;
    private ListView mListView;

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
        return (fragmentRootView = inflater.inflate(R.layout.fragment_order, container, false));
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        toolbar.setTitle(R.string.fragment_order_title);
        toolbar.getMenu().clear();
    }


    public static void dispatchTouchEvent(MotionEvent ev) {
        // MUST send event to coopscrollhandler, but note we DO NOT depend on the response
        mCoopScrollHandler.dispatchTouchEvent(ev);

    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mSomeScroller = (ScrollView) fragmentRootView.findViewById(R.id.outer_scroll);
        mListView = (ListView) fragmentRootView.findViewById(R.id.rabbit_reviews_list);
        ArrayList<String> datas = new ArrayList<String>();
        for(int i=0; i< 40; i++){
            datas.add("Item : " + i);
        }
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_expandable_list_item_1, datas);
        mListView.setAdapter(stringArrayAdapter);


        mCoopScrollHandler = new CooperativeScrollGestureListener(getActivity(), mSomeScroller, new CooperativeScrollGestureListener.ICoopInnerScrollableView() {
            @Override
            public boolean isScrollableAtTop() {
                return isInnerScrollerAtTop();
            }
            @Override
            public boolean isScrollableAtBottom() {
                return isInnerScrollerAtBottom();
            }
        });
    }





    private boolean isInnerScrollerAtTop() {
        if(null != mListView) {
            if(Build.VERSION.SDK_INT >= 14) {
                return !mListView.canScrollVertically(-1);
            } else {
                return !ViewCompat.canScrollVertically(mListView, -1);
            }
        }
        return true;
    }

    private boolean isInnerScrollerAtBottom() {
        if(null != mListView) {
            if(Build.VERSION.SDK_INT >= 14) {
                return !mListView.canScrollVertically(1);
            } else {
                return !ViewCompat.canScrollVertically(mListView, 1);
            }
        }
        return true;
    }
}
