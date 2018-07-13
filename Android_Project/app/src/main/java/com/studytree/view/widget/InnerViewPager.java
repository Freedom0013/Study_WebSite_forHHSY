package com.studytree.view.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PointF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Banner自定义滑动事件ViewPager
 * Title: InnerViewPager
 * @author Freedom0013
 * @date 2018/7/12 12:01
 */
public class InnerViewPager extends ViewPager {
    private static final String TAG = InnerViewPager.class.getSimpleName();
    PointF downPoint = new PointF();
    OnSingleTouchListener onSingleTouchListener;

    /**
     * 构造函数
     */
    public InnerViewPager(@NonNull Context context) {
        super(context);
    }

    /**
     * 构造函数
     */
    public InnerViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        //如果不是第一个页面、自己处理滑动事件
//        if(getCurrentItem() != 0 || getCurrentItem() != 3){
//            getParent().requestDisallowInterceptTouchEvent(true);
//        }else{  //当在第一个页面时交给父控件处理滑动事件
//            getParent().requestDisallowInterceptTouchEvent(false);
//        }
        return super.dispatchTouchEvent(ev);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent evt) {
        switch (evt.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 记录按下时候的坐标
                downPoint.x = evt.getX();
                downPoint.y = evt.getY();
//                if (this.getChildCount() > 1) { //有内容，多于1个时
//                    // 通知其父控件，现在进行的是本控件的操作，不允许拦截
//                    getParent().requestDisallowInterceptTouchEvent(true);
//                }
                break;
            case MotionEvent.ACTION_MOVE:
//                if (this.getChildCount() > 0) { //有内容，多于1个时
//                    // 通知其父控件，现在进行的是本控件的操作，不允许拦截
//                    getParent().requestDisallowInterceptTouchEvent(true);
//                }
                //由于父控件、侧滑菜单和Banner相互抢夺点击事件，此处相当于暂时禁用了Banner的滑动事件
                return false;
            case MotionEvent.ACTION_UP:
                // 在up时判断是否按下和松手的坐标为一个点
                if (PointF.length(evt.getX() - downPoint.x, evt.getY() - downPoint.y) < (float) 5.0) {
                    onSingleTouch(this);
                    return true;
                }
                break;
        }
        return super.onTouchEvent(evt);
    }

    /**
     * 单击
     * @param v 被单击界面
     */
    public void onSingleTouch(View v) {
        if (onSingleTouchListener != null) {
            onSingleTouchListener.onSingleTouch(v);
        }
    }

    /**
     * Banner单击事件监听
     * Title: InnerViewPager
     * @date 2018/7/13 15:01
     * @author Freedom0013
     */
    public interface OnSingleTouchListener {
        /**
         * 单击事件
         * @param v 被单击界面
         */
        void onSingleTouch(View v);
    }

    /**
     * 设置单击事件监听
     * @param onSingleTouchListener 监听器
     */
    public void setOnSingleTouchListener(OnSingleTouchListener onSingleTouchListener) {
        this.onSingleTouchListener = onSingleTouchListener;
    }
}
