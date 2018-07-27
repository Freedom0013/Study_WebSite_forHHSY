package com.studyplatform.web.utils;

import com.studyplatform.web.system.SystemCommonValue;

/**
 * 调试信息控制类
 * Title: DebugUtils
 * @date 2018年1月21日
 * @author Freedom0013
 */
public class DebugUtils {
	/**
	 * 调试信息调用方法
	 * @param message 需要显示的信息String
	 */
	public static void showLog(String message) {
		if (SystemCommonValue.isDebug) {
		    if(SystemCommonValue.Logger){
		        System.out.println("----Study_Platform----Logger:"+message);
		    }
		}
	}
	
	/**
     * 调试信息调用方法
     * @param message 需要显示的信息boolean
     */
	public static void showLog(boolean message) {
        if (SystemCommonValue.isDebug) {
            if(SystemCommonValue.Logger){
                System.out.println("----Study_Platform----Logger:"+message);
            }
        }
    }
	
	/**
     * 调试信息调用方法
     * @param message 需要显示的信息int
     */
	public static void showLog(int message) {
        if (SystemCommonValue.isDebug) {
            if(SystemCommonValue.Logger){
                System.out.println("----Study_Platform----Logger:"+message);
            }
        }
    }
	
	/**
     * 调试信息调用方法
     * @param message 需要显示的信息String
     */
	public static void showError(String message) {
	    if(SystemCommonValue.isDebug){
	        if(SystemCommonValue.Error){
                System.out.println("----Study_Platform----Error:"+message);
            }
	    }
	}
	
	/**
     * 调试信息调用方法
     * @param message 需要显示的信息boolean
     */
    public static void showError(boolean message) {
        if(SystemCommonValue.isDebug){
            if(SystemCommonValue.Error){
                System.out.println("----Study_Platform----Error:"+message);
            }
        }
    }
    
    /**
     * 调试信息调用方法
     * @param message 需要显示的信息int
     */
    public static void showError(int message) {
        if(SystemCommonValue.isDebug){
            if(SystemCommonValue.Error){
                System.out.println("----Study_Platform----Error:"+message);
            }
        }
    }
}