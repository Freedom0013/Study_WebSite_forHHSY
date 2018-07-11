package com.studytree.view.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.studytree.log.Logger;

//        <com.studytree.view.widget.CircularImage
//        android:id="@+id/menu_avatar"
//        android:layout_width="50dp"
//        android:layout_height="50dp"
//        android:layout_marginTop="10dp"
//        android:layout_marginLeft="15dp"
//        android:layout_marginBottom="10dp"
//        android:layout_gravity="center_vertical"
//        android:src="@drawable/left_student_icon" />

/**
 * 圆形ImageView
 * Title: CircularImage
 * @author Freedom0013
 * @date 2018/7/11 12:11
 */
public class CircularImage extends MaskedImage {
    private static final String TAG = CircularImage.class.getSimpleName();

    public CircularImage(Context paramContext) {
        super(paramContext);
    }

    public CircularImage(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
    }

    public CircularImage(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
    }

    public Bitmap createMask() {
        int i = getWidth();
        int j = getHeight();
        Bitmap.Config localConfig = Bitmap.Config.ARGB_4444;
        try {
            Bitmap localBitmap = Bitmap.createBitmap(i, j, localConfig);
            Canvas localCanvas = new Canvas(localBitmap);
            Paint localPaint = new Paint(1);
            localPaint.setColor(-16777216);
            float f1 = getWidth();
            float f2 = getHeight();
            RectF localRectF = new RectF(0.0F, 0.0F, f1, f2);
            localCanvas.drawOval(localRectF, localPaint);
            return localBitmap;
        } catch (Exception e) {
            Logger.e(TAG, "圆形ImageView异常", e);
        }
        return null;
    }
}
