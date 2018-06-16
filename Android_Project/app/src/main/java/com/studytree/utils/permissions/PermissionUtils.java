package com.studytree.utils.permissions;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.widget.Toast;

import com.studytree.log.Logger;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.app.ActivityCompat.requestPermissions;

/**
 * 权限工具类
 * Title: PermissionUtils
 * @date 2018/6/15 17:58
 * @author Freedom0013
 */
public class PermissionUtils implements ActivityCompat.OnRequestPermissionsResultCallback {
    public static final String TAG = PermissionUtils.class.getSimpleName();
    /** 权限请求显示提示 */
    public String PermissionHintText;
    /** 权限结果监听 */
    private RequestPermissionListener listener = null;

    /**
     * 构造函数
     */
    public PermissionUtils(){

    }

    /**
     * 请求权限构造函数
     * @param listener 权限申请结果监听器
     */
    public PermissionUtils(RequestPermissionListener listener){
        this.listener = listener;
    }

    /**
     * 单个权限检测，true不需要请求权限，false需要请求权限
     * @param permissionName 权限名称
     * @return 是否有该权限
     */
    public boolean isPermissionGranted(String permissionName, Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        //判断是否需要请求允许权限
        int hasPermision = context.checkSelfPermission(permissionName);
        if (hasPermision != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        return true;
    }

    /**
     * 多个权限检测，返回list,如果list.size为空说明权限全部有了不需要请求，否则请求没有的
     * @param permArray 权限组
     * @return 结果集合
     */
    public List<String> isPermissionsAllGranted(String[] permArray, Context context) {
        List<String> list = new ArrayList<>();
        //获得批量请求但被禁止的权限列表
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return list;
        }
        for (int i = 0; permArray != null && i < permArray.length; i++) {
            if (PackageManager.PERMISSION_GRANTED != context.checkSelfPermission(permArray[i])) {
                list.add(permArray[i]);
            }
        }
        return list;
    }

    /**
     * 单个权限请求
     * @param s 权限组
     * @param requestCode 请求码
     * @param defeat 权限提示
     * @param activity Activity对象
     */
    public void Requestpermission(String s, int requestCode, String defeat, final Activity activity) {
        PermissionHintText = defeat;
        if (!TextUtils.isEmpty(s)) {
            boolean granted = isPermissionGranted(s,activity);
            if (granted) {  //有权限，调用方法
                listener.onPermissionPass(requestCode);
            } else {
                requestPermissions(activity,new String[]{s}, requestCode);
            }
        }
    }

    /**
     * 多个权限请求
     * @param s 权限组
     * @param requestCode 请求码
     * @param defeat 权限提示
     * @param activity Activity对象
     */
    public void Requestpermission(String s[], int requestCode, String defeat, final Activity activity) {
        PermissionHintText = defeat;
        if (s.length != 0) {
            List<String> perList = isPermissionsAllGranted(s, activity);
            if (perList.size() == 0) {  //有权限，调用方法
                listener.onPermissionPass(requestCode);
            } else {
                requestPermissions(activity,perList.toArray(new String[perList.size()]), requestCode);
            }
        }
    }

    /**
     * 6.0权限获取结果回调
     * @param requestCode  权限请求码
     * @param permissions  权限组
     * @param grantResults 请求结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Logger.w(TAG, "=====权限批准回调======"+requestCode);
        for (int i : grantResults) {
            //有权限未通过，用户拒绝权限
            if (i != PackageManager.PERMISSION_GRANTED) {
                Logger.w(TAG, PermissionConfig.getPermissionToString(requestCode) + "权限请求失败！");
                listener.onPermissionAccreditFailed(requestCode);
                return;
            }
            //用户授予权限
            Logger.i(TAG, PermissionConfig.getPermissionToString(requestCode) + "权限请求成功！");
        }
        listener.onPermissionAccreditSucceed(requestCode);
    }

    /**
     * 用户点击不再询问时弹出的解释提示框
     * @param PermissionHintText 提示
     * @param context Context对象
     */
    public void popPermissionAlterDialog(String PermissionHintText,final Context context) {
        new AlertDialog.Builder(context)
                .setTitle("权限提示")
                .setMessage(PermissionHintText)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //前往应用详情界面
                        try {
                            Uri packUri = Uri.parse("package:" + context.getPackageName());
                            Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packUri);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        } catch (Exception e) {
                            Toast.makeText(context, "跳转失败", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                }).create().show();
    }

    /**
     * 设置权限监听
     * @param listener 监听器
     */
    public void setRequestPermissionListener(RequestPermissionListener listener){
        this.listener = listener;
    }
}
