package com.studytree.view.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.studytree.R;
import com.studytree.log.Logger;
import com.studytree.utils.StudyTreeTools;

/**
<com.studytree.view.widget.CircleProgress
 xmlns:app="http://schemas.android.com/apk/res-auto"
 android:id="@+id/circle_progress"
 android:layout_width="200dp"
 android:layout_height="200dp"
 android:layout_gravity="center_horizontal"
 app:antiAlias="true"
 app:arcWidth="5dp"
 app:bgArcColor="@android:color/darker_gray"
 app:bgArcWidth="5dp"
 app:hint="当前进度"
 app:hintSize="25sp"
 app:maxValue="100"
 app:startAngle="270"
 app:sweepAngle="360"
 app:unit="%"
 app:unitSize="25sp"
 app:value="100"
 app:valueSize="35sp" />

 int[] COLORS = new int[]{Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE};
 reset();
 setGradientColors(COLORS);
 setValue(XXXX);
 */

/**
 * 圆形进度条
 * Title: CircleProgress
 * @date 2018/7/6 11:58
 * @author Freedom0013
 */
public class CircleProgress extends View {
    private static final String TAG = CircleProgress.class.getSimpleName();
    /** 抗锯齿开关 */
    public static final boolean ANTI_ALIAS = true;
    /** 默认大小 */
    public static final int DEFAULT_SIZE = 150;
    /** 默认圆弧起始角度，3点钟方向为0，顺时针递增，小于0或大于360进行取余 */
    public static final int DEFAULT_START_ANGLE = 270;
    /** 默认圆弧度数 */
    public static final int DEFAULT_SWEEP_ANGLE = 360;
    /** 默认动画时间 */
    public static final int DEFAULT_ANIM_TIME = 1000;
    /** 默认最大值 */
    public static final int DEFAULT_MAX_VALUE = 100;
    /** 默认值 */
    public static final int DEFAULT_VALUE = 50;
    /** 默认提示大小 */
    public static final int DEFAULT_HINT_SIZE = 15;
    /** 默认单位大小 */
    public static final int DEFAULT_UNIT_SIZE = 30;
    /** 默认值大小 */
    public static final int DEFAULT_VALUE_SIZE = 15;
    /** 默认画笔宽度 */
    public static final int DEFAULT_ARC_WIDTH = 15;
    /** Context */
    private Context mContext;
    /** 默认大小 */
    private int mDefaultSize;
    /** 是否开启抗锯齿 */
    private boolean antiAlias;
    /** 绘制提示画笔 */
    private TextPaint mHintPaint;
    /** 内容 */
    private CharSequence mHint;
    /** 内容颜色 */
    private int mHintColor;
    /** 内容大小 */
    private float mHintSize;
    /** 内容y坐标 */
    private float mHintOffset;
    /** 绘制单位（百分号） */
    private TextPaint mUnitPaint;
    /** 单位 */
    private CharSequence mUnit;
    /** 单位颜色 */
    private int mUnitColor;
    /** 单位大小 */
    private float mUnitSize;
    /** 单位y坐标 */
    private float mUnitOffset;
    /** 绘制数值 */
    private TextPaint mValuePaint;
    /** 当前值 */
    private float mValue;
    /** 最大值 */
    private float mMaxValue;
    /** 当前值y坐标 */
    private float mValueOffset;
    /** 精度，默认为0 */
    private int mPrecision;
    /** 精度格式化字符串 */
    private String mPrecisionFormat;
    /** 当前值颜色 */
    private int mValueColor;
    /** 当前值大小 */
    private float mValueSize;
    /** 绘制圆弧 */
    private Paint mArcPaint;
    /** 画笔粗细 */
    private float mArcWidth;
    /** 圆弧起始角度，3点钟方向为0，顺时针递增，小于0或大于360进行取余 */
    private float mStartAngle;
    /** 圆弧度数 */
    private float mSweepAngle;
    /** RectF */
    private RectF mRectF;
    /** 渐变的颜色是360度，如果只显示270，那么则会缺失部分颜色 */
    private SweepGradient mSweepGradient;
    /** 进度条渐变颜色数组 */
    private int[] mGradientColors = {Color.GREEN};
    /** 当前进度，[0.0f,1.0f] */
    private float mPercent;
    /** 动画时间 */
    private long mAnimTime;
    /** 属性动画 */
    private ValueAnimator mAnimator;
    /** 绘制背景圆弧 */
    private Paint mBgArcPaint;
    /** 背景圆弧颜色 */
    private int mBgArcColor;
    /** 背景圆弧宽度 */
    private float mBgArcWidth;
    /** 圆心坐标，半径 */
    private Point mCenterPoint;
    /** 圆弧半径 */
    private float mRadius;
    /** 文字的偏移量，相对于圆半径而言，默认三分之一 */
    private float mTextOffsetPercentInRadius;

    /**
     * CircleProgress构造函数
     * @param context Context对象
     * @param attrs 参数
     */
    public CircleProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    /**
     * 初始化
     * @param context Context对象
     * @param attrs 参数
     */
    private void init(Context context, AttributeSet attrs) {
        mContext = context;
        mDefaultSize = StudyTreeTools.dip2px(DEFAULT_SIZE);
        mAnimator = new ValueAnimator();
        mRectF = new RectF();
        mCenterPoint = new Point();
        initAttrs(attrs);
        initPaint();
        setValue(mValue);
    }

    /**
     * 初始化资源参数
     * @param attrs 参数
     */
    private void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.CircleProgressBar);

        antiAlias = typedArray.getBoolean(R.styleable.CircleProgressBar_antiAlias, ANTI_ALIAS);

        mHint = typedArray.getString(R.styleable.CircleProgressBar_hint);
        mHintColor = typedArray.getColor(R.styleable.CircleProgressBar_hintColor, Color.BLACK);
        mHintSize = typedArray.getDimension(R.styleable.CircleProgressBar_hintSize, DEFAULT_HINT_SIZE);

        mValue = typedArray.getFloat(R.styleable.CircleProgressBar_value, DEFAULT_VALUE);
        mMaxValue = typedArray.getFloat(R.styleable.CircleProgressBar_maxValue, DEFAULT_MAX_VALUE);
        //内容数值精度格式
        mPrecision = typedArray.getInt(R.styleable.CircleProgressBar_precision, 0);
        mPrecisionFormat = getPrecisionFormat(mPrecision);
        mValueColor = typedArray.getColor(R.styleable.CircleProgressBar_valueColor, Color.BLACK);
        mValueSize = typedArray.getDimension(R.styleable.CircleProgressBar_valueSize, DEFAULT_VALUE_SIZE);

        mUnit = typedArray.getString(R.styleable.CircleProgressBar_unit);
        mUnitColor = typedArray.getColor(R.styleable.CircleProgressBar_unitColor, Color.BLACK);
        mUnitSize = typedArray.getDimension(R.styleable.CircleProgressBar_unitSize, DEFAULT_UNIT_SIZE);

        mArcWidth = typedArray.getDimension(R.styleable.CircleProgressBar_arcWidth, DEFAULT_ARC_WIDTH);
        mStartAngle = typedArray.getFloat(R.styleable.CircleProgressBar_startAngle, DEFAULT_START_ANGLE);
        mSweepAngle = typedArray.getFloat(R.styleable.CircleProgressBar_sweepAngle, DEFAULT_SWEEP_ANGLE);

        mBgArcColor = typedArray.getColor(R.styleable.CircleProgressBar_bgArcColor, Color.WHITE);
        mBgArcWidth = typedArray.getDimension(R.styleable.CircleProgressBar_bgArcWidth, DEFAULT_ARC_WIDTH);
        mTextOffsetPercentInRadius = typedArray.getFloat(R.styleable.CircleProgressBar_textOffsetPercentInRadius, 0.33f);

        mAnimTime = typedArray.getInt(R.styleable.CircleProgressBar_animTime, DEFAULT_ANIM_TIME);

        //读取渐变颜色数组
        int gradientArcColors = typedArray.getResourceId(R.styleable.CircleProgressBar_arcColors, 0);
        if (gradientArcColors != 0) {
            try {
                int[] gradientColors = getResources().getIntArray(gradientArcColors);
                if (gradientColors.length == 0) {//如果渐变色为数组为0，则尝试以单色读取色值
                    int color = getResources().getColor(gradientArcColors);
                    mGradientColors = new int[2];
                    mGradientColors[0] = color;
                    mGradientColors[1] = color;
                } else if (gradientColors.length == 1) {//如果渐变数组只有一种颜色，默认设为两种相同颜色
                    mGradientColors = new int[2];
                    mGradientColors[0] = gradientColors[0];
                    mGradientColors[1] = gradientColors[0];
                } else {
                    mGradientColors = gradientColors;
                }
            } catch (Resources.NotFoundException e) {
                throw new Resources.NotFoundException("资源未找到！");
            }
        }
        typedArray.recycle();
    }

    /**
     * 获取数值精度格式化字符串
     * @param precision
     * @return 数值字符串
     */
    public static String getPrecisionFormat(int precision) {
        return "%." + precision + "f";
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        mHintPaint = new TextPaint();
        // 设置抗锯齿,会消耗较大资源，绘制图形速度会变慢。
        mHintPaint.setAntiAlias(antiAlias);
        // 设置绘制文字大小
        mHintPaint.setTextSize(mHintSize);
        // 设置画笔颜色
        mHintPaint.setColor(mHintColor);
        // 从中间向两边绘制，不需要再次计算文字
        mHintPaint.setTextAlign(Paint.Align.CENTER);

        mValuePaint = new TextPaint();
        mValuePaint.setAntiAlias(antiAlias);
        mValuePaint.setTextSize(mValueSize);
        mValuePaint.setColor(mValueColor);
        // 设置Typeface对象，即字体风格，包括粗体，斜体以及衬线体，非衬线体等
        mValuePaint.setTypeface(Typeface.DEFAULT_BOLD);
        mValuePaint.setTextAlign(Paint.Align.CENTER);

        mUnitPaint = new TextPaint();
        mUnitPaint.setAntiAlias(antiAlias);
        mUnitPaint.setTextSize(mUnitSize);
        mUnitPaint.setColor(mUnitColor);
        mUnitPaint.setTextAlign(Paint.Align.CENTER);

        mArcPaint = new Paint();
        mArcPaint.setAntiAlias(antiAlias);
        // 设置画笔的样式，为FILL，FILL_OR_STROKE，或STROKE
        mArcPaint.setStyle(Paint.Style.STROKE);
        // 设置画笔粗细
        mArcPaint.setStrokeWidth(mArcWidth);
        // 当画笔样式为STROKE或FILL_OR_STROKE时，设置笔刷的图形样式，如圆形样式
        // Cap.ROUND,或方形样式 Cap.SQUARE
        mArcPaint.setStrokeCap(Paint.Cap.ROUND);

        mBgArcPaint = new Paint();
        mBgArcPaint.setAntiAlias(antiAlias);
        mBgArcPaint.setColor(mBgArcColor);
        mBgArcPaint.setStyle(Paint.Style.STROKE);
        mBgArcPaint.setStrokeWidth(mBgArcWidth);
        mBgArcPaint.setStrokeCap(Paint.Cap.ROUND);
    }
    /** 测量出所绘制 View 的大小 */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(mymeasure(widthMeasureSpec, mDefaultSize),mymeasure(heightMeasureSpec, mDefaultSize));
    }

    /**
     * 测量View(根据父容器传递跟子容器的大小要求来确定子容器的大小。)
     * @param measureSpec 父View传递过来的信息
     * @param defaultSize View 的默认大小
     * @return
     */
    public int mymeasure(int measureSpec, int defaultSize) {
        /*
         * 通过MeasureSpec这个类可以获取父View传递过来的一些信息，包括MODE、SIZE属性。这里做一下说明
         *      MODE:分为一下三种类别,
         *      AT_MOST：子容器可以是声明大小内的任意大小
         *      EXACTLY:父容器已经为子容器确定的大小，子容器应该遵守
         *      UNSPECIFIED:父容器对子容器没有做任何限制，子容器可以任意大小
         * SIZE是父容器为子容器提供的大小
         *      当MODE为AT_MOST时，SIZE大小为父容器所能提供的最大值。
         *      当MODE为EXACTLY时，SIZE为父容器提供的限制值。
         *      当MODE为UNSPECIFIED时，大小为0，SIZE完全由子容器的大小决定。
         */
        int result = defaultSize;
        int specMode = View.MeasureSpec.getMode(measureSpec);
        int specSize = View.MeasureSpec.getSize(measureSpec);
        if (specMode == View.MeasureSpec.EXACTLY) {
            result = specSize;
        } else if (specMode == View.MeasureSpec.AT_MOST) {
            result = Math.min(result, specSize);
        }
        return result;
    }

    /**
     * 计算绘制圆及文字所需的参数
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Logger.d(TAG, "onSizeChanged: w = " + w + "; h = " + h + "; oldw = " + oldw + "; oldh = " + oldh);
        //求圆弧和背景圆弧的最大宽度
        float maxArcWidth = Math.max(mArcWidth, mBgArcWidth);
        //求最小值作为实际值
        int minSize = Math.min(w - getPaddingLeft() - getPaddingRight() - 2 * (int) maxArcWidth,
                h - getPaddingTop() - getPaddingBottom() - 2 * (int) maxArcWidth);
        //减去圆弧的宽度，否则会造成部分圆弧绘制在外围
        mRadius = minSize / 2;
        //获取圆的相关参数
        mCenterPoint.x = w / 2;
        mCenterPoint.y = h / 2;
        //绘制圆弧的边界
        mRectF.left = mCenterPoint.x - mRadius - maxArcWidth / 2;
        mRectF.top = mCenterPoint.y - mRadius - maxArcWidth / 2;
        mRectF.right = mCenterPoint.x + mRadius + maxArcWidth / 2;
        mRectF.bottom = mCenterPoint.y + mRadius + maxArcWidth / 2;
        //计算文字绘制时的 baseline
        //由于文字的baseline、descent、ascent等属性只与textSize和typeface有关，所以此时可以直接计算
        //若value、hint、unit由同一个画笔绘制或者需要动态设置文字的大小，则需要在每次更新后再次计算
        mValueOffset = mCenterPoint.y + getBaselineOffsetFromY(mValuePaint);
        mHintOffset = mCenterPoint.y - mRadius * mTextOffsetPercentInRadius + getBaselineOffsetFromY(mHintPaint);
        mUnitOffset = mCenterPoint.y + mRadius * mTextOffsetPercentInRadius + getBaselineOffsetFromY(mUnitPaint);

        //设置渐变颜色
//        updateArcPaint();

        Logger.d(TAG, "onSizeChanged: 控件大小 = " + "(" + w + ", " + h + ")"
                + "圆心坐标 = " + mCenterPoint.toString()
                + ";圆半径 = " + mRadius
                + ";圆的外接矩形 = " + mRectF.toString());
    }

    /**
     * 计算文字绘制时的 baseline
     * @param paint 画笔
     * @return YBaseline
     */
    private float getBaselineOffsetFromY(Paint paint) {
        return measureTextHeight(paint) / 2;
    }

    /**
     * 测量文字高度
     * @param paint
     * @return
     */
    public static float measureTextHeight(Paint paint) {
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return (Math.abs(fontMetrics.ascent) - fontMetrics.descent);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawText(canvas);
        drawArc(canvas);
    }

    /**
     * 绘制内容文字
     * @param canvas
     */
    private void drawText(Canvas canvas) {
        // 计算文字宽度，由于Paint已设置为居中绘制，故此处不需要重新计算
        // float textWidth = mValuePaint.measureText(mValue.toString());
        // float x = mCenterPoint.x - textWidth / 2;
        canvas.drawText(String.format(mPrecisionFormat, mValue), mCenterPoint.x, mValueOffset, mValuePaint);

        if (mHint != null) {
            canvas.drawText(mHint.toString(), mCenterPoint.x, mHintOffset, mHintPaint);
        }

        if (mUnit != null) {
            canvas.drawText(mUnit.toString(), mCenterPoint.x, mUnitOffset, mUnitPaint);
        }
    }

    /**
     * 绘制圆弧
     * @param canvas Canvas
     */
    private void drawArc(Canvas canvas) {
        // 绘制背景圆弧
        // 从进度圆弧结束的地方开始重新绘制，优化性能
        canvas.save();
        float currentAngle = mSweepAngle * mPercent;
        canvas.rotate(mStartAngle, mCenterPoint.x, mCenterPoint.y);
        canvas.drawArc(mRectF, currentAngle, mSweepAngle - currentAngle + 2, false, mBgArcPaint);
        // 第一个参数 oval 为 RectF 类型，即圆弧显示区域
        // startAngle 和 sweepAngle  均为 float 类型，分别表示圆弧起始角度和圆弧度数
        // 3点钟方向为0度，顺时针递增
        // 如果 startAngle < 0 或者 > 360,则相当于 startAngle % 360
        // useCenter:如果为True时，在绘制圆弧时将圆心包括在内，通常用来绘制扇形
        canvas.drawArc(mRectF, 2, currentAngle, false, mArcPaint);
        canvas.restore();
    }

    /**
     * 更新圆弧画笔
     */
    private void updateArcPaint() {
        // 设置渐变
        mSweepGradient = new SweepGradient(mCenterPoint.x, mCenterPoint.y, mGradientColors, null);
        mArcPaint.setShader(mSweepGradient);
    }

    /**
     * 获取抗锯齿开关
     * @return 抗锯齿
     */
    public boolean isAntiAlias() {
        return antiAlias;
    }

    /**
     * 获取内容值
     * @return 内容
     */
    public CharSequence getHint() {
        return mHint;
    }

    /**
     * 设置内容值
     * @param hint 内容
     */
    public void setHint(CharSequence hint) {
        mHint = hint;
    }

    /**
     * 获取单位
     * @return 单位
     */
    public CharSequence getUnit() {
        return mUnit;
    }

    /**
     * 设置单位
     * @param unit 单位
     */
    public void setUnit(CharSequence unit) {
        mUnit = unit;
    }

    /**
     * 获取当前值
     * @return 当前值
     */
    public float getValue() {
        return mValue;
    }

    /**
     * 设置当前值
     * @param value 值
     */
    public void setValue(float value) {
        if (value > mMaxValue) {
            value = mMaxValue;
        }
        float start = mPercent;
        float end = value / mMaxValue;
        startAnimator(start, end, mAnimTime);
    }

    /**
     * 进度条动画
     * @param start 起始值
     * @param end 结束值
     * @param animTime 时间
     */
    private void startAnimator(float start, float end, long animTime) {
        mAnimator = ValueAnimator.ofFloat(start, end);
        mAnimator.setDuration(animTime);
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mPercent = (float) animation.getAnimatedValue();
                mValue = mPercent * mMaxValue;
                Logger.d(TAG, "onAnimationUpdate: percent = " + mPercent
                        + ";currentAngle = " + (mSweepAngle * mPercent)
                        + ";value = " + mValue);
                invalidate();
            }
        });
        mAnimator.start();
    }

    /**
     * 获取最大值
     * @return 最大值
     */
    public float getMaxValue() {
        return mMaxValue;
    }

    /**
     * 设置最大值
     * @param maxValue 最大值
     */
    public void setMaxValue(float maxValue) {
        mMaxValue = maxValue;
    }

    /**
     * 获取精度
     * @return 精度
     */
    public int getPrecision() {
        return mPrecision;
    }

    /**
     * 设置精度
     * @return 精度
     */
    public void setPrecision(int precision) {
        mPrecision = precision;
        mPrecisionFormat = getPrecisionFormat(precision);
    }

    /**
     * 获取渐变
     */
    public int[] getGradientColors() {
        return mGradientColors;
    }

    /**
     * 设置渐变
     * @param gradientColors 颜色
     */
    public void setGradientColors(int[] gradientColors) {
        mGradientColors = gradientColors;
        updateArcPaint();
    }

    /**
     * 获取动画时间
     * @return 动画时间
     */
    public long getAnimTime() {
        return mAnimTime;
    }

    /**
     * 设置动画时间
     * @param animTime 动画时间
     */
    public void setAnimTime(long animTime) {
        mAnimTime = animTime;
    }

    /**
     * 重置
     */
    public void reset() {
        startAnimator(mPercent, 0.0f, 1000L);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        //释放资源
    }
}
