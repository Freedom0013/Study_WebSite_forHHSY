package com.studytree.utils;

import java.io.File;

import android.os.Environment;
import android.os.StatFs;

/**
 * 空间状态工具类
 * Title: MemoryStatus
 * @date 2018/6/14 18:06
 * @author Freedom0013
 */
public class MemoryStatus {
    /** 错误标识 */
    private static final int ERROR = -1;
    /** 预设大小2M */
    private static final long RESERVED_SIZE = 2097152;

    /**
     * 空参构造函数
     */
    private MemoryStatus() {
    }

    /**
     * 判断SD卡是否存在
     * @return 结果
     */
    public static boolean externalMemoryAvailable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取内部存储可用大小
     * @return 结果
     */
    @SuppressWarnings("deprecation")
    public static long getAvailableInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return availableBlocks * blockSize;
    }

    /**
     * 获取内部存储总大小
     * @return 结果
     */
    @SuppressWarnings("deprecation")
    public static long getTotalInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return totalBlocks * blockSize;
    }

    /**
     * 获取可用外部存储大小
     * @return 结果
     */
    @SuppressWarnings("deprecation")
    public static long getAvailableExternalMemorySize() {
        if (externalMemoryAvailable()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long availableBlocks = stat.getAvailableBlocks();
            return availableBlocks * blockSize;
        } else {
            return ERROR;
        }
    }

    /**
     * 获取外部存储总大小
     * @return
     */
    @SuppressWarnings("deprecation")
    public static long getTotalExternalMemorySize() {
        if (externalMemoryAvailable()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long totalBlocks = stat.getBlockCount();
            return totalBlocks * blockSize;
        } else {
            return ERROR;
        }
    }

    /**
     * 格式化大小
     * @param size 大小
     * @return 格式化结果
     */
    public static String formatSize(long size) {
        String suffix = "B";

        if (size >= 1024) {
            suffix = "KiB";
            size /= 1024;
            if (size >= 1024) {
                suffix = "MiB";
                size /= 1024;
            }
        }

        StringBuilder resultBuffer = new StringBuilder(Long.toString(size));

        int commaOffset = resultBuffer.length() - 3;
        while (commaOffset > 0) {
            resultBuffer.insert(commaOffset, ',');
            commaOffset -= 3;
        }

        if (suffix != null) {
            resultBuffer.append(suffix);
        }
        return resultBuffer.toString();
    }

    /**
     * 判断内部存储是否有足够空间
     * @param size 被判断空间大小
     * @return 结果
     */
    public static boolean isInternalMemoryAvailable(long size) {
        long availableMemory = getAvailableInternalMemorySize();
        return !(size > availableMemory);
    }

    /**
     * 判断外部存储是否有足够空间
     * @param size 被判断空间大小
     * @return 结果
     */
    public static boolean isExternalMemoryAvailable(long size) {
        long availableMemory = getAvailableExternalMemorySize();
        return !(size > availableMemory);
    }


    /**
     * 判断手机是否拥有可用空间
     * @param size 被测大小
     * @return 结果
     */
    public static boolean isMemoryAvailable(long size) {
        size += RESERVED_SIZE;
        if (externalMemoryAvailable()) {
            return isExternalMemoryAvailable(size);
        }
        return isInternalMemoryAvailable(size);
    }

    /**
     * 获取指定地址可用大小
     * @param path 地址
     * @return 大小
     */
    @SuppressWarnings("deprecation")
    public static long getSpecificMemoryAvaliable(String path) {
        StatFs stat = new StatFs(path);
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return availableBlocks * blockSize;
    }

    /**
     * 判断指定地址大小是否可用
     * @param size 大小
     * @param path 地址
     * @return 结果
     */
    public static boolean isSpecificMemoryAvailable(long size, String path) {
        long availableMemory = getSpecificMemoryAvaliable(path);
        return !(size > availableMemory);
    }
}
