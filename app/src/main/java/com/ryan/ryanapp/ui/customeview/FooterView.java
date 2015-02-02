package com.ryan.ryanapp.ui.customeview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ryan.ryanapp.R;
import com.ryan.ryanapp.RyanApp;
import com.ryan.ryanapp.Utils.UnitFormatter;


public class FooterView extends LinearLayout {

    private View footerView;
    private ProgressBar progressBar;
    private TextView loadingText;

    public FooterView(Context context, AttributeSet attrs) {

        super(context, attrs);
        initFooterView();
    }

    public FooterView(Context context) {

        super(context);
        initFooterView();
    }

    private void initFooterView() {

        footerView = View.inflate(getContext(), R.layout.footerview_layout, this);
        progressBar = (ProgressBar) footerView.findViewById(R.id.progressBar);
        loadingText = (TextView) footerView.findViewById(R.id.loadingText);
        footerView.setBackgroundResource(R.color.transparent);
        LayoutParams params = new LayoutParams(RyanApp.instance.getScreentSize().getWidth(), (int) UnitFormatter.dp2px(56));
        params.topMargin = 1;
        params.gravity = Gravity.CENTER;
        footerView.setLayoutParams(params);
    }

    public void setLoadingText(String text) {

        loadingText.setText(text);
    }

    public void setLoadingText(int textResid) {

        loadingText.setText(textResid);
    }

    public void setFooterVisibility(int visibility) {

        footerView.setVisibility(visibility);
    }

    public void setProgressBarVisibility(int visibility) {

        progressBar.setVisibility(visibility);
    }
}
