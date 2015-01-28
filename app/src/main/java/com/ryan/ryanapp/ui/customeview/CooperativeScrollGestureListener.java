package com.ryan.ryanapp.ui.customeview;
/**
 * 作者: Ryan
 * 创建时间:15/1/28 17:31
 * 类描述:
 */
import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewGroup;

/**
 * Detects if scroll gesture happens, and if the secondary (often, the "inner" scroller is
 * at the edge in the gesture's direction, have the primary scrollable process the gesture
 */
public class CooperativeScrollGestureListener implements GestureDetector.OnGestureListener {

    private boolean isInGesture = false;
    private boolean isScrollingVertical = false;

    private ViewGroup mPrimaryView;
    private ICoopInnerScrollableView mSecondaryView;

    private GestureDetector mGestureDetector;

    /**
     * You may pass in 'null' for the 'secondaryView' parameter if you do NOT want the outer
     * view to scroll when you hit the top/bottom of the inner scroll view.  This will still
     * make the inner scroll work when inside an outer scrollable view.
     *
     * @param ctx
     * @param primaryView   Usually the "outer" scrollable view you want cooperating
     * @param secondaryView Usually the "inner" scrollable (null = ignore overscroll)
     */
    public CooperativeScrollGestureListener(Context ctx, ViewGroup primaryView, ICoopInnerScrollableView secondaryView) {
        mPrimaryView = primaryView;
        mSecondaryView = secondaryView;
        mGestureDetector = new GestureDetector(ctx, this);
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distX, float distY) {
        if(!isInGesture) {
            isInGesture = true;
            isScrollingVertical = (Math.abs(distY) > Math.abs(distX));
        }
        if(!isScrollingVertical)
            return false;

        if(distY < 0 && null != mSecondaryView && mSecondaryView.isScrollableAtTop()) {
            // VIEW GOING UP
            mPrimaryView.requestDisallowInterceptTouchEvent(false);

        } else if(distY > 0 && null != mSecondaryView && mSecondaryView.isScrollableAtBottom()) {
            // VIEW GOING DOWN
            mPrimaryView.requestDisallowInterceptTouchEvent(false);

        } else {
            // Pulling up but at bottom of secondary view OR pulling down and at top
            mPrimaryView.requestDisallowInterceptTouchEvent(true);
        }
        return false;
    }

    // This MUST return true for DOWN event to have this see subsequent events
    @Override
    public boolean onDown(MotionEvent e) {
        isInGesture = false;
        isScrollingVertical = true;
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        // nothing
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        isInGesture = false;
        isScrollingVertical = false;
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        // nothing
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float vX, float vY) {
        return false;
    }

    // ---------------------- methods -----------------------

    /**
     * Your Activity must call this in it's dispatchTouchEvent() override.
     *
     * @param ev
     */
    public void dispatchTouchEvent(MotionEvent ev) {
        mGestureDetector.onTouchEvent(ev);
    }

    /**
     * The scrollable views (ScrollView, ListView, etc) should be wrapped in this interface
     * to be generically handled by this listener.  The operations done in these method
     * calls should not be computationally intensive since it would otherwise affect
     * scrolling performance.
     */
    public interface ICoopInnerScrollableView {
        public boolean isScrollableAtTop();
        public boolean isScrollableAtBottom();
    }
}