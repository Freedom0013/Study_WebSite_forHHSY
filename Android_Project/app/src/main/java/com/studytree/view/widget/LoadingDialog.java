package com.studytree.view.widget;

import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.Window;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.studytree.R;

/**
 * 加载弹框
 * Title: LoadingDialog
 * @date 2018/7/3 19:25
 * @author Freedom0013
 */
public class LoadingDialog extends Dialog {
    private static final String TAG = LoadingDialog.class.getSimpleName();
    /** Context对象 */
    private Context context;
    /** LoadingDialog对象 */
    private static LoadingDialog dialog;
    /** LoadingView */
    private LoadingView loading;
    /** TextView */
    private TextView textView;

    /**
     * LoadingDialog构造方法
     * @param context Context对象
     */
    public LoadingDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    /**
     * 显示LoadingDialog
     * @param context Context对象
     * @return LoadingDialog
     */
    public static LoadingDialog showDialog(Context context) {
        dialog = new LoadingDialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置背景透明（此项必须，否则圆角将不应用）
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_loading);//dialog布局文件
        dialog.setCanceledOnTouchOutside(false);//点击外部不允许关闭dialog
        dialog.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                    return true;
                }
                return false;
            }
        });
        return dialog;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && dialog != null) {
            loading = dialog.findViewById(R.id.loading);
            ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
            animator.setDuration(700);
            animator.setRepeatCount(-1);
            animator.setInterpolator(new LinearInterpolator());
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    loading.computeUpdateValue((float) animation.getAnimatedValue());
                }
            });
            animator.start();
            textView = dialog.findViewById(R.id.loading_text);
        }
    }
}
