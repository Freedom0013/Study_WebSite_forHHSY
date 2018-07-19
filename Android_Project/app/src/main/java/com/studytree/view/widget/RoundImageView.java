package com.studytree.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.studytree.R;
import com.studytree.utils.StudyTreeTools;

/**
 * 改进型圆角ImageView
 * Title: RoundImageView
 * @date 2018/7/19 17:51
 * @author Freedom0013
 */
public class RoundImageView extends ImageView {
    /** 圆形模式 */
    private static final int MODE_CIRCLE = 1;
    /** 普通模式 */
    private static final int MODE_NONE = 0;
    /** 圆角模式 */
    private static final int MODE_ROUND = 2;
    /** 画笔 */
    private Paint mPaint;
    /** 绘制模式 */
    private int currMode = 0;
    /** 圆角半径 */
    private int currRound = StudyTreeTools.dip2px(5);

    /** 构造函数 */
    public RoundImageView(Context context) {
        super(context);
        initViews();
    }

    /** 构造函数 */
    public RoundImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /** 构造函数 */
    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        obtainStyledAttrs(context, attrs, defStyleAttr);
        initViews();
    }

    /**
     * 获取StyledAttrs
     */
    private void obtainStyledAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoundImageView, defStyleAttr, 0);
        currMode = a.hasValue(R.styleable.RoundImageView_type) ? a.getInt(R.styleable.RoundImageView_type, MODE_NONE) : MODE_NONE;
        currRound = a.hasValue(R.styleable.RoundImageView_radius) ? a.getDimensionPixelSize(R.styleable.RoundImageView_radius, currRound) : currRound;
        a.recycle();
    }

    /**
     * 初始化View
     */
    private void initViews() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    }

    //自定义View尺寸的规则
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //当模式为圆形模式的时候，强制让宽高一致
        if (currMode == MODE_CIRCLE) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            int result = Math.min(getMeasuredHeight(), getMeasuredWidth());
            setMeasuredDimension(result, result);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    /**
     * ——————————————————————解释——————————————————————————
     *
     * (x1,y1)             -90度
     *      |----------------|-----------------|
     *      |                |                 |
     *      |                |                 |
     *      |                |                 |
     *      |----------------|=================|  0度数方向
     *      |                |                 |
     *      |                |                 |
     *      |                |                 |
     *      |----------------|-----------------|
     *                     +90度               (x2,y2)
     *  RectF函数会根据RectF(x1,y1,x2,y2)，(x1,y1)(x2,y2)，坐标绘制一个矩形
     *  方法一：
     *      Path path = new Path();
     *      path.arcTo(new RectF(x1,y1,x2,y2), 起始度数, 结束度数);可以绘制四分之一个上图圆弧
     *  方法二：
     *      1.使用drawRoundRect绘制圆角矩形
     *          public void drawRoundRect (RectF rect, float rx, float ry, Paint paint)
     *              参数说明：rect：RectF对象。rx：x方向上的圆角半径。ry：y方向上的圆角半径。paint：绘制时所使用的画笔。
     *      2.使用drawCircle绘制圆形
     *          public void drawCircle (float cx, float cy, float radius, Paint paint)
     *              参数说明：cx：圆心的x坐标。cy：圆心的y坐标。radius：圆的半径。paint：绘制时所使用的画笔。
     */
    /**
     * 绘制
     * @param canvas 画布对象
     */
    @Override
    protected void onDraw(Canvas canvas) {
        Drawable mDrawable = getDrawable();
        Matrix mDrawMatrix = getImageMatrix();
        if (mDrawable == null) {
            return;
        }
        if (mDrawable.getIntrinsicWidth() == 0 || mDrawable.getIntrinsicHeight() == 0) {
            return;
        }
        if (mDrawMatrix == null && getPaddingTop() == 0 && getPaddingLeft() == 0) {
            mDrawable.draw(canvas);
        } else {
            final int saveCount = canvas.getSaveCount();
            canvas.save();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                if (getCropToPadding()) {
                    final int scrollX = getScrollX();
                    final int scrollY = getScrollY();
                    canvas.clipRect(scrollX + getPaddingLeft(), scrollY + getPaddingTop(),
                            scrollX + getRight() - getLeft() - getPaddingRight(),
                            scrollY + getBottom() - getTop() - getPaddingBottom());
                }
            }
            canvas.translate(getPaddingLeft(), getPaddingTop());
            //当为圆形模式的时候
            if (currMode == MODE_CIRCLE) {
                Bitmap bitmap = drawable2Bitmap(mDrawable);
                mPaint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
                canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2, mPaint);
            } else if (currMode == MODE_ROUND) {  //当为圆角模式的时候
                Bitmap bitmap = drawable2Bitmap(mDrawable);
                mPaint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
                canvas.drawRoundRect(new RectF(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom()),
                        currRound, currRound, mPaint);
            } else {
                if (mDrawMatrix != null) {
                    canvas.concat(mDrawMatrix);
                }
                mDrawable.draw(canvas);
            }
            canvas.restoreToCount(saveCount);
        }
    }

    /**
     * drawable转换成bitmap
     * @param drawable drawable
     * @return bitmap
     */
    private Bitmap drawable2Bitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        //根据传递的scaletype获取matrix对象，设置给bitmap
        Matrix matrix = getImageMatrix();
        if (matrix != null) {
            canvas.concat(matrix);
        }
        drawable.draw(canvas);
        return bitmap;
    }
}
