package com.studytree.view.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.studytree.R;
import com.studytree.utils.StringUtils;

/**
 * 系统标题栏
 * Title: StudyTreeTitleBar
 * @date 2018/7/15 21:57
 * @author Freedom0013
 */
public class StudyTreeTitleBar extends Toolbar implements View.OnClickListener{
    private static final String TAG = StudyTreeTitleBar.class.getSimpleName();
    /** Context对象 */
    private Context mContext;
    /** 左边按钮 */
    private ImageView titlebar_left_btn;
    /** 中间title */
    private TextView titlebar_text;
    /** 右边按钮 */
    private ImageView titlebar_right_btn;
    /** 点击事件监听器 */
    private TitleBarClickListener mListener;

    public StudyTreeTitleBar(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public StudyTreeTitleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public StudyTreeTitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    /**
     * 初始化标题栏View
     */
    private void initView() {
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        View mRootView = mInflater.inflate(R.layout.layout_titlebar,null);
        titlebar_left_btn = mRootView.findViewById(R.id.titlebar_left_btn);

        titlebar_text = mRootView.findViewById(R.id.titlebar_text);
        titlebar_text.setTextColor(Color.WHITE);
        titlebar_text.setClickable(true);
        titlebar_text.setSingleLine(true);
        titlebar_text.setText(getResources().getString(R.string.app_name));

        titlebar_right_btn = mRootView.findViewById(R.id.titlebar_right_btn);

        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER_HORIZONTAL);
        titlebar_left_btn.setOnClickListener(this);
        titlebar_text.setOnClickListener(this);
        titlebar_right_btn.setOnClickListener(this);
        addView(mRootView,lp);
    }

    /**
     * 设置右边按钮图片
     * @param resid 资源id
     */
    public void setRightDrawable(int resid){
        titlebar_right_btn.setImageDrawable(ContextCompat.getDrawable(mContext,resid));
    }

    /**
     * 设置左边按钮图片
     * @param resid 资源id
     */
    public void setLeftDrawable(int resid){
        titlebar_left_btn.setImageDrawable(ContextCompat.getDrawable(mContext,resid));
    }

    /**
     * 设置标题栏文字
     * @param text 文字
     */
    public void setTitle(String text){
        if (!StringUtils.isNullOrEmpty(text)) {
            titlebar_text.setText(text);
        }
    }

    /**
     * 设置右边按钮显隐
     * @param visibility false隐藏
     */
    public void setTitleRightVisibility(boolean visibility) {
        titlebar_right_btn.setVisibility(visibility ? View.VISIBLE: View.GONE);
    }

    /**
     * 设置左边按钮显隐
     * @param visibility false隐藏
     */
    public void setTitleLeftVisibility(boolean visibility) {
        titlebar_left_btn.setVisibility(visibility ? View.VISIBLE: View.GONE);
    }

    /**
     * 设置title显隐
     * @param visibility false隐藏
     */
    public void setTitleVisibility(boolean visibility) {
        titlebar_text.setVisibility(visibility ? View.VISIBLE: View.GONE);
    }

    /**
     * 设置标题栏点击事件监听
     * @param lis 标题栏点击事件监听
     */
    public void setOnTitleBarClickedListener(TitleBarClickListener  lis){
        this.mListener = lis;
    }

    @Override
    public void onClick(View v) {
        if (v.equals(titlebar_left_btn)) {
            if(mListener != null){
                mListener.onTitleLeftClicked();
            }
        } else if (v.equals(titlebar_text)) {
            if(mListener != null){
                mListener.onTitleClicked();
            }
        } else if (v.equals(titlebar_right_btn)) {
            if(mListener != null){
                mListener.onTitleRightClicked();
            }
        }
    }

    /**
     * 标题栏点击事件监听器
     * Title: StudyTreeTitleBar
     * @date 2018/7/15 22:00
     * @author Freedom0013
     */
    public interface TitleBarClickListener{
        /** 左边按钮点击事件 */
        void onTitleLeftClicked();
        /** 右边按钮点击事件 */
        void onTitleRightClicked();
        /** 标题栏点击事件 */
        void onTitleClicked();
    }
}
