package com.studytree.utils.permissions;

import java.util.List;

/**
 * 权限请求结果监听
 * Title: RequestPermissionListener
 * @date 2018/6/16 22:35
 * @author Freedom0013
 */
public interface RequestPermissionListener {
    /**
     * 应用已拥有此权限
     * @param requestCode 权限请求码
     */
    void onPermissionPass(int requestCode);

    /**
     * 用户授予权限
     * @param requestCode 权限请求码
     */
    void onPermissionAccreditSucceed(int requestCode);

    /**
     * 用户拒绝权限
     * @param requestCode 权限请求码
     */
    void onPermissionAccreditFailed(int requestCode, String PermissionName);
}
