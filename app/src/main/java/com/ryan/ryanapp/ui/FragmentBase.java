package com.ryan.ryanapp.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ryan.ryanapp.Utils.LogUtils;

import java.util.Map;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment must implement the
 * {@link FragmentBase.OnFragmentInteractionListener} interface to handle interaction events.
 * Use the {@link FragmentBase#newInstance} factory method to create an instance of this fragment.
 */
public class FragmentBase extends Fragment {

    protected  String TAG;
    protected OnFragmentInteractionListener mListener;
    protected View fragmentRootView;
    protected Toolbar toolbar;


    public FragmentBase() {
        TAG = getClass().getSimpleName();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtils.i(TAG, "onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtils.i(TAG, "onActivityCreated(@Nullable Bundle savedInstanceState)");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        LogUtils.i(TAG, "onAttach(Activity activity)");
        try {
            mListener = (OnFragmentInteractionListener) activity;
            toolbar = ((ActivityBase) activity).toolbar;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.i(TAG, "onPause()");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.i(TAG, "onResume()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
        LogUtils.i(TAG, "onDetach()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.i(TAG, "onDestroy()");
    }

    /**
     * This interface must be implemented by activities that contain this fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that activity.
     * <p/>See the Android Training lesson <a href= "http://developer.android.com/training/basics/fragments/communicating.html">Communicating with Other Fragments</a>
     * for more information.
     */
    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Map<String, Object> args);
    }

}
