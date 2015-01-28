package com.ryan.ryanapp.ui.customeview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author yt
 *         类描述 	可以截取触摸事件的RecyclerView
 *         创建日期 ： 2014年12月25日 下午5:30:50
 */
public class TouchableRecyclerView extends RecyclerView {

    private boolean touchable = true;

    public TouchableRecyclerView(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);
    }

    public TouchableRecyclerView(Context context, AttributeSet attrs) {

        super(context, attrs);
    }

    public TouchableRecyclerView(Context context) {

        super(context);
    }

    /**
     * 当前RecyclerView是否可以获取触摸事件
     *
     * @param touchable true可以获取触摸事件， false不可以获取触摸事件
     */
    public void setTouchable(boolean touchable) {

        this.touchable = touchable;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        if (touchable)
            return super.dispatchTouchEvent(ev);
        return true;
    }


}
