package com.studytree.http.logic.download;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.FileProvider;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.studytree.InitManager;
import com.studytree.R;
import com.studytree.log.Logger;
import com.studytree.utils.StudyTreeTools;

import java.io.File;
import java.io.IOException;

public class DownloadProgressManager {
    private static final String TAG = DownloadProgressManager.class.getSimpleName();
    /** activity对象 */
    private Activity mActivity;
    private Handler handler;
    private DownloadListener mlistener;
    private AlertDialog dialog;
    private ProgressBar download_progress;
    private TextView download_text;
    private static final int UPDATA_UI = 0x11;

    public  static final int REQUEST_INSTALL_CODE = 0x12;

    private Handler mhandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case UPDATA_UI:
                    Bundle bundle = msg.getData();
                    long progress = bundle.getLong("progress");
                    long currentLength = bundle.getLong("currentLength");

                    int current = new Long(currentLength).intValue();
                    int intprogress = new Long(progress).intValue();
                    download_progress.setMax(current);
                    Logger.d(TAG, "当前进度==" + intprogress+"|||||||"+current);
                    download_progress.setProgress(intprogress);

                    float temp = progress/(float)currentLength;
                    int tempprogress = (int) (temp * 100);
                    Logger.d(TAG, "正在下载："+tempprogress+"%");
                    download_text.setText("正在下载："+tempprogress+"%");
                    if (tempprogress == 100) {
                        //下载完成
                        if (dialog != null) {
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                            dialog = null;
                        }
                    }
                    break;
            }
        }
    };

    public DownloadProgressManager(Activity activity, DownloadListener listener){
        this.mActivity = activity;
        this.mlistener = listener;
        handler = new Handler(mActivity.getMainLooper());
    }

    public void showDownloadDialog(final String fileName,final String downloadURL) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setCancelable(false);
        dialog = builder.create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置背景透明（此项必须，否则圆角将不应用）
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);//点击外部不允许关闭dialog
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                    return true;
                }
                return false;
            }
        });
        dialog.show();
        final Window window = dialog.getWindow();
        android.view.WindowManager.LayoutParams params = window.getAttributes();
        params.width = InitManager.getInstance().getScreenWidth() - StudyTreeTools.dip2px(70);
        window.setAttributes(params);
        window.setContentView(R.layout.dialog_downloading);
        download_progress = window.findViewById(R.id.download_progress);
        download_text = window.findViewById(R.id.download_text);
        doDownload(fileName,downloadURL);
    }

    private void doDownload(final String fileName,final String downloadURL) {
        //TODO:是否再进行一次权限检查及确认下载文件目录是否存在
        // 设置progressBar初始化
        download_progress.setProgress(0);
        DownloadDispatcher.getInstance().startDownload(fileName, downloadURL, new DownloadCallback() {
            @Override
            public void onSuccess(final File file) {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mActivity,"下载成功，进行安装！",Toast.LENGTH_SHORT).show();
                        installNormal(mActivity,file);
                    }
                });
            }

            @Override
            public void onFailure(Exception e) {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mActivity,"下载失败！",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onProgress(final long progress, final long currentLength) {
                Message message = new Message();
                message.what = UPDATA_UI;
                Bundle bundle = new Bundle();
                bundle.putLong("progress", progress);
                bundle.putLong("currentLength", currentLength);
                message.setData(bundle);
                mhandler.sendMessage(message);
            }

            @Override
            public void onPause(long progress, long currentLength) {
                //没有暂停操作
            }
        });
    }

    //普通安装
    private static void installNormal(Context context, File file) {
        setPermission(file.getPath());
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //版本在7.0以上是不能直接通过uri访问的
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
            // 由于没有在Activity环境下启动Activity,设置下面的标签
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
            Uri apkUri = FileProvider.getUriForFile(context, "com.studytree.fileprovider", file);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
        }
        context.startActivity(intent);
    }

    public static void setPermission(String filePath)  {
        String command = "chmod " + "777" + " " + filePath;
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
