package com.studytree.view.widget;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.studytree.R;

/**
 * 新手引导页ViewPager页面
 * Title: IntroViewCreator
 * @date 2018/7/6 17:24
 * @author Freedom0013
 */
public class IntroViewCreator {
    public static final String TAG = IntroViewCreator.class.getSimpleName();
    private ImageView mImageView;

    public IntroViewCreator() {

    }

    @SuppressLint("InflateParams")
    public View getView(LayoutInflater inflater, int resId) {
        View v = inflater.inflate(R.layout.layout_item_intro, null);
        mImageView = v.findViewById(R.id.intro_image);
        mImageView.setBackgroundResource(resId);
        return v;
    }

    public void release() {

    }
}
