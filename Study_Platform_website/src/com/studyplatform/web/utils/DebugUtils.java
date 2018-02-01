package com.studyplatform.web.utils;

/**
 * 调试信息控制类
 * Title: DebugUtils
 * @date 2018年1月21日
 * @author Freedom0013
 */
public class DebugUtils {
	/** 是否显示调试信息标识 */
    public static final boolean isDebug = true;
	
	/**
	 * 调试信息调用方法
	 * @param message 需要显示的信息String
	 */
	public static void showLog(String message) {
		if (isDebug) {
			System.out.println("----Study_Platform----"+message);
		}
	}
	
	/**
     * 调试信息调用方法
     * @param message 需要显示的信息boolean
     */
	public static void showLog(boolean message) {
        if (isDebug) {
            System.out.println("----Study_Platform----"+message);
        }
    }
	
	/**
     * 调试信息调用方法
     * @param message 需要显示的信息int
     */
	public static void showLog(int message) {
        if (isDebug) {
            System.out.println("----Study_Platform----"+message);
        }
    }
}