package com.ryan.ryanapp.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import java.util.Iterator;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment must implement the
 * {@link FragmentBase.OnFragmentInteractionListener} interface to handle interaction events.
 * Use the {@link FragmentBase#newInstance} factory method to create an instance of this fragment.
 */
public class FragmentBase extends Fragment {

    private OnFragmentInteractionListener mListener;
    protected View fragmentRootView;

    /**
     * Use this factory method to create a new instance of this fragment using the provided parameters.
     * @return A new instance of fragment FragmentBase.
     */
    public static FragmentBase newInstance(Map<String, String> params) {
        FragmentBase fragment = new FragmentBase();
        Bundle args = new Bundle();
        Iterator<String> iterator = params.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            args.putString(key, params.get(key));
        }
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentBase() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
