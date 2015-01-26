package com.ryan.ryanapp.ui;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ryan.ryanapp.R;

import java.util.HashMap;

public class ActivityMain extends ActivityBase {

    public static final int MAIN_TAB = 1;
    public static final int ORDER_TAB = 2;
    public static final int MESSAGE_TAB = 3;
    public static final int ME_TAB = 4;
    private int currentTab;

    @Override
    protected void initView() {
        toolbar.setTitle("MainActivity");
        toolbar.setNavigationIcon(R.drawable.ic_launcher);
        toolbar.inflateMenu(R.menu.menu_main);
        baseViewContainer.addView(View.inflate(this, R.layout.activity_main, null));
        View mainTab = baseViewContainer.findViewById(R.id.mainTab);
        View orderTab = baseViewContainer.findViewById(R.id.orderTab);
        View messageTab = baseViewContainer.findViewById(R.id.messageTab);
        View meTab = baseViewContainer.findViewById(R.id.meTab);
        mainTab.setOnClickListener(this);
        orderTab.setOnClickListener(this);
        messageTab.setOnClickListener(this);
        meTab.setOnClickListener(this);
        setCurrentTab(MAIN_TAB);
    }

    public void setCurrentTab(int position) {
        if (position == currentTab) {
            return;
        }
        ImageView mainTabImage = (ImageView) baseViewContainer.findViewById(R.id.mainTabImage);
        TextView mainTabTitle = (TextView) baseViewContainer.findViewById(R.id.mainTabTitle);
        ImageView orderTabImage = (ImageView) baseViewContainer.findViewById(R.id.orderTabImage);
        TextView orderTabTitle = (TextView) baseViewContainer.findViewById(R.id.orderTabTitle);
        ImageView messageTabImage = (ImageView) baseViewContainer.findViewById(R.id.messageTabImage);
        TextView messageTabTitle = (TextView) baseViewContainer.findViewById(R.id.messageTabTitle);
        ImageView meTabImage = (ImageView) baseViewContainer.findViewById(R.id.meTabImage);
        TextView meTabTitle = (TextView) baseViewContainer.findViewById(R.id.meTabTitle);
        mainTabImage.setImageResource(R.drawable.main_normal);
        orderTabImage.setImageResource(R.drawable.order_normal);
        messageTabImage.setImageResource(R.drawable.message_normal);
        meTabImage.setImageResource(R.drawable.me_normal);
        mainTabTitle.setTextColor(getResources().getColor(R.color.black));
        orderTabTitle.setTextColor(getResources().getColor(R.color.black));
        messageTabTitle.setTextColor(getResources().getColor(R.color.black));
        meTabTitle.setTextColor(getResources().getColor(R.color.black));
        switch (position) {
            case MAIN_TAB:
                switchFragment(FragmentMain.newInstance(new HashMap<String, String>()), R.id.activityMainContainer, false);
                mainTabImage.setImageResource(R.drawable.main_selected);
                mainTabTitle.setTextColor(getResources().getColor(R.color.theme_color));
                break;
            case MESSAGE_TAB:
                switchFragment(FragmentMessage.newInstance(new HashMap<String, String>()), R.id.activityMainContainer, false);
                messageTabImage.setImageResource(R.drawable.message_selected);
                messageTabTitle.setTextColor(getResources().getColor(R.color.theme_color));
                break;
            case ORDER_TAB:
                switchFragment(FragmentOrder.newInstance(new HashMap<String, String>()), R.id.activityMainContainer, false);
                orderTabImage.setImageResource(R.drawable.order_selected);
                orderTabTitle.setTextColor(getResources().getColor(R.color.theme_color));
                break;
            case ME_TAB:
                switchFragment(FragmentMe.newInstance(new HashMap<String, String>()), R.id.activityMainContainer, false);
                meTabImage.setImageResource(R.drawable.me_selected);
                meTabTitle.setTextColor(getResources().getColor(R.color.theme_color));
                break;
        }
        currentTab = position;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.mainTab:
                setCurrentTab(MAIN_TAB);
                break;
            case R.id.orderTab:
                setCurrentTab(ORDER_TAB);
                break;
            case R.id.messageTab:
                setCurrentTab(MESSAGE_TAB);
                break;
            case R.id.meTab:
                setCurrentTab(ME_TAB);
                break;
        }
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
