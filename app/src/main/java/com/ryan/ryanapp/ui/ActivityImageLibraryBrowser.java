package com.ryan.ryanapp.ui;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.ryan.ryanapp.R;

public class ActivityImageLibraryBrowser extends ActivityBase {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.setCustomAnimations(R.anim.open_right_in, R.anim.close_left_out);
		FragmentImageLibraryBrowser libraryBrowser = new FragmentImageLibraryBrowser();
		transaction.replace(R.id.baseViewContainer, libraryBrowser, FragmentImageLibraryBrowser.class.getSimpleName());
		transaction.commit();
	}
    @Override protected void initView() {
        toolbar.setTitle("选择图片");
    }

}
