package com.ryan.ryanapp.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ryan.ryanapp.R;

public class ActivityMain extends ActivityBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseViewContainer.addView(View.inflate(this, R.layout.activity_main, null));
    }

    @Override
    protected void initView() {
        toolbar.setTitle("MainActivity");
        toolbar.setNavigationIcon(R.drawable.ic_launcher);
        toolbar.inflateMenu(R.menu.menu_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
