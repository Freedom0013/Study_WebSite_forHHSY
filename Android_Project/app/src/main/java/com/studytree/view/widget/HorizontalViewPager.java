package com.studytree.view.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 自定义处理点击事件ViewPager
 * Title: HorizontalViewPager
 * @date 2018/7/10 17:56
 * @author Freedom0013
 */
public class HorizontalViewPager extends ViewPager {
    public HorizontalViewPager(@NonNull Context context) {
        super(context);
    }

    public HorizontalViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //如果不是第一个页面、自己处理滑动事件
        if(getCurrentItem() != 0){
            getParent().requestDisallowInterceptTouchEvent(true);
        }else{  //当在第一个页面时交给父控件处理滑动事件
            getParent().requestDisallowInterceptTouchEvent(false);
        }
        return super.dispatchTouchEvent(ev);
    }
}
