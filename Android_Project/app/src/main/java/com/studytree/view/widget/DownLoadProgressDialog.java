package com.studytree.view.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.Window;

import com.studytree.R;

/**
 * 多线程下载Dialog
 * Title: DownLoadProgressDialog
 * @date 2018/7/3 23:01
 * @author Freedom0013
 */
public class DownLoadProgressDialog extends Dialog {
    private static final String TAG = DownLoadProgressDialog.class.getSimpleName();
    /** Context对象 */
    private Context context;
    /** LoadingDialog对象 */
    private static DownLoadProgressDialog dialog;

    public DownLoadProgressDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    /**
     * 显示LoadingDialog
     * @param context Context对象
     * @return LoadingDialog
     */
    public static DownLoadProgressDialog showDialog(Context context) {
        dialog = new DownLoadProgressDialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置背景透明（此项必须，否则圆角将不应用）
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_downloading);//dialog布局文件
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


}
