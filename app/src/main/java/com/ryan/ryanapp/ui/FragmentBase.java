package com.ryan.ryanapp.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ryan.ryanapp.R;
import com.ryan.ryanapp.Utils.LogUtils;
import com.ryan.ryanapp.ui.customeview.TianmaDailogBuilder;

import java.util.Map;

public class FragmentBase extends Fragment implements Handler.Callback, View.OnClickListener {

    protected String TAG;
    protected OnFragmentInteractionListener mListener;
    protected View fragmentRootView;
    protected Toolbar toolbar;
    protected Handler baseHandler;
    protected AlertDialog dialog;

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
            toolbar.setVisibility(View.VISIBLE);
            baseHandler = new Handler(this);
            toolbar.setNavigationOnClickListener(this);
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        //        LogUtils.i(TAG, "onPause()");
    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setNavigationIcon(R.drawable.ic_launcher);
        //        LogUtils.i(TAG, "onResume()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //        LogUtils.i(TAG, "onDetach()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //        LogUtils.i(TAG, "onDestroy()");
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

    @Override public boolean handleMessage(Message msg) {
        return false;
    }

    /**
     * 显示加载页面
     * @return 当前的对话框对象
     * @param loadingText 加载提示文字
     */
    protected void showLoadingDialog(String loadingText) {

        if (dialog == null) {
            TianmaDailogBuilder tianmaDailogBuilder = new TianmaDailogBuilder(getActivity());
            View loadingView = View.inflate(getActivity(), R.layout.loading_layout, null);
            TextView loadTextView = (TextView) loadingView.findViewById(R.id.loadingText);
            loadTextView.setText(loadingText);
            tianmaDailogBuilder.setCustomContent(false, loadingView, false);
            dialog = tianmaDailogBuilder.create();
            dialog.show();
        } else {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            dialog.show();
        }
    }

    /**
     * 显示加载页面
     * @return 当前的对话框对象
     */
    protected void showLoadingDialog() {

        showLoadingDialog(getString(R.string.loading));
    }

    protected void dissmissLoadingDialog() {

        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override public void onClick(View v) {
        if(v.getId() == -1){
            LogUtils.i(TAG, "点击了返回键");
            getActivity().onBackPressed();
        }
    }
}
