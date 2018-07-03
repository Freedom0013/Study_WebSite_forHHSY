package com.studytree.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.FloatRange;
import android.util.AttributeSet;
import android.view.View;

import com.studytree.R;
import com.studytree.utils.StudyTreeTools;

/**
 * 加载控件
 * Title: LoadingView
 * @date 2018/7/3 16:18
 * @author Freedom0013
 */
public class LoadingView extends View {
    /** 默认大小（dp） */
    private static final float DEFAULT_SIZE = 50.0f;
    /** 外圈大圆度数 */
    private static final int OUTER_CIRCLE_ANGLE = 270;
    /** 内圈圆度数 */
    private static final int INTER_CIRCLE_ANGLE = 90;
    /** 画笔 */
    private Paint mStrokePaint;
    /** 外圈 */
    private RectF mOuterCircleRectF;
    /** 内圈 */
    private RectF mInnerCircleRectF;
    /** 起始位置（动画变量） */
    private int mRotateAngle;
    /** 控件宽度 */
    private float mViewWidth;
    /** 控件高度 */
    private float mViewHeight;

    /**
     * 使用默认大小构造函数
     * @param context Context对象
     * @param attrs Xml属性
     */
    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //正方形控件长宽相等
        mViewWidth = StudyTreeTools.dip2px(DEFAULT_SIZE);
        mViewHeight = StudyTreeTools.dip2px(DEFAULT_SIZE);
        //外圈半径
        float outR = StudyTreeTools.dip2px(DEFAULT_SIZE * 0.5f - 10);
        //内圈半径
        float inR = outR * 0.6f;

        initPaint(inR * 0.4f);
        mRotateAngle = 0;
        mOuterCircleRectF = new RectF();
        mOuterCircleRectF.set(mViewWidth * 0.5f - outR, mViewHeight * 0.5f - outR, mViewWidth * 0.5f + outR, mViewHeight * 0.5f + outR);
        mInnerCircleRectF = new RectF();
        mInnerCircleRectF.set(mViewWidth * 0.5f - inR, mViewHeight * 0.5f - inR, mViewWidth * 0.5f + inR, mViewHeight * 0.5f + inR);
    }

    /**
     * 初始化画笔
     * @param lineWidth 画笔宽度
     */
    private void initPaint(float lineWidth) {
        mStrokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setStrokeWidth(lineWidth);
        mStrokePaint.setColor(getResources().getColor(R.color.loading_color));
        mStrokePaint.setDither(true);
        mStrokePaint.setFilterBitmap(true);
        mStrokePaint.setStrokeCap(Paint.Cap.ROUND);
        mStrokePaint.setStrokeJoin(Paint.Join.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        //绘制时由mRotateAngle改变，从而改变初始画笔位置，从而达到旋转动画的目的
        canvas.drawArc(mOuterCircleRectF, mRotateAngle % 360, OUTER_CIRCLE_ANGLE, false, mStrokePaint);
        canvas.drawArc(mInnerCircleRectF, 270 - mRotateAngle % 360, INTER_CIRCLE_ANGLE, false, mStrokePaint);
        canvas.restore();
    }

    /**
     * 设置不透明度
     * @param alpha 0~255
     */
    public void setAlpha(int alpha) {
        mStrokePaint.setAlpha(alpha);
    }

    /**
     * 重绘动画
     * @param animatedValue 重绘值
     */
    public void computeUpdateValue(@FloatRange(from = 0.0, to = 1.0) float animatedValue){
        mRotateAngle = (int) (360 * animatedValue);
        invalidate();
    }
}
