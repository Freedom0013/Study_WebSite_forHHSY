package com.studytree.view.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioButton;

import com.studytree.utils.StudyTreeTools;

/**
 * 支付单选按钮
 * Title: PayRadioButton
 * @author Freedom0013
 * @date 2018/7/30 21:22
 */
public class PayRadioButton extends RadioButton {
    private int mLeftDrawableHeight;
    private int mIconSize;

    public PayRadioButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public PayRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PayRadioButton(Context context) {
        super(context);
        init();
    }

    private void init() {
        mLeftDrawableHeight = StudyTreeTools.dip2px(getContext(), 35);
        mIconSize = StudyTreeTools.dip2px(getContext(), 16);

        Drawable leftDrawable = getCompoundDrawables()[0];
        Drawable iconDrawable = getCompoundDrawables()[2];

        if (leftDrawable != null) {
            int leftDrawableWidth = leftDrawable.getIntrinsicWidth() * mLeftDrawableHeight / leftDrawable.getIntrinsicHeight();
            leftDrawable.setBounds(0, 0, leftDrawableWidth, mLeftDrawableHeight);
        }

        if (iconDrawable != null) {
            iconDrawable.setBounds(0, 0, mIconSize, mIconSize);
        }

        setCompoundDrawables(leftDrawable, null, iconDrawable, null);
    }
}
