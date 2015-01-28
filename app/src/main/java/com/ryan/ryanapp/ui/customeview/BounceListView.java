package com.ryan.ryanapp.ui.customeview;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;
/**
 * 作者: Ryan
 * 创建时间:15/1/28 17:20
 * 类描述:
 */
public class BounceListView extends ListView {

    public BounceListView(Context context) {
        super(context);
    }
    public BounceListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public BounceListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, 200, isTouchEvent);
    }

}
