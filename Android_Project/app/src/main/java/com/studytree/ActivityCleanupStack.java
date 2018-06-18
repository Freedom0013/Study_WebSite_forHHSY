package com.studytree;

import android.app.Activity;

import com.studytree.log.Logger;

import java.util.Stack;

/**
 * Activity任务栈管理
 * Title: ActivityCleanupStack
 * @date 2018/6/18 16:31
 * @author Freedom0013
 */
public class ActivityCleanupStack {
    private static final String TAG = ActivityCleanupStack.class.getSimpleName();
    /** 任务栈元素 */
    private static Stack<Activity> mStack = new Stack<Activity>();

    /**
     * 判断栈是否为空
     * @return 栈状态
     */
    public static boolean isEmpty() {
        return mStack.isEmpty();
    }

    /**
     * Activity压栈
     * @param activity 压栈Activity
     */
    public static void push(Activity activity) {
        mStack.push(activity);
    }

    /**
     * 弹栈，从栈中删除并返回指定Activity
     * @param activity 指定Activity
     * @return 弹栈指定Activity
     */
    public static Activity pop(Class<?> activity) {
        try {
            if (mStack == null || mStack.isEmpty()) {
                return null;
            }
            for (Activity act : mStack) {
                if (act.getClass().equals(activity)) {
                    mStack.remove(act);
                    return act;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            doException(e);
        }
        return null;
    }

    /**
     * 弹栈2，从栈中删除指定Activity并finish掉此Activity
     * @param activity 匹指定ACtivity
     */
    public static void pop2(Activity activity) {
        if (activity == null || mStack == null || mStack.isEmpty()) {
            return;
        }
        try {
            if (mStack != null && mStack.contains(activity)) {
                activity.finish();
                mStack.remove(activity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            doException(e);
        }
    }

    /**
     * 从栈中删除指定Activity
     * @param activity 待删除指定Activity
     */
    public static void removeActivity(Class<?> activity) {
        if (mStack.size() <= 0) {
            return;
        }
        for (Activity act : mStack) {
            if (act.getClass().equals(activity)) {
                act.finish();
            }
        }
    }

    /**
     * 弹出栈顶单个Activity并返回
     * @return Activity 弹栈对象
     */
    public static Activity pop() {
        if (mStack.size() <= 0) {
            return null;
        }
        Activity activity = mStack.pop();
        return activity;
    }

    /**
     * 弹栈并finish掉Activity对象
     */
    public static void popAndDestroy() {
        if (mStack.size() <= 0) {
            return;
        }
        Activity activity = mStack.pop();
        activity.finish();
    }

    /**
     * 弹栈栈顶count个Activity
     * @param count 弹栈数量
     */
    public static void popAndDestroy(int count) {
        for (int i = 0; i < count; i++) {
            if (mStack.size() == 0) {
                break;
            }
            Activity activity = mStack.pop();
            activity.finish();
        }
    }

    /**
     * 清理栈顶指定activity
     * @param activity 参数1
     */
    public static void clearTopActivities(Class<?> activity) {
        if (mStack.size() <= 0) {
            return;
        }
        while (!mStack.isEmpty() && !mStack.peek().getClass().equals(activity)) {
            Activity act = mStack.pop();
            act.finish();
        }
    }

    /**
     * 清理除activity、secondClass之外所有Activity
     * @param activity 参数1
     * @param secondClass 参数2
     */
    public static void clearTopActivities(Class<?> activity, Class<?> secondClass) {
        if (mStack.size() <= 0) {
            return;
        }
        while (!mStack.isEmpty() && !mStack.peek().getClass().equals(activity) && !mStack.peek().equals(secondClass)) {
            Activity temp_activity = mStack.pop();
            temp_activity.finish();
        }
    }

    /**
     * 默认finish掉Main之外所有activity，忽略当前activity
     */
    public static void clearAllExcept(Class<?> activity) {
        if (mStack.size() <= 0) {
            return;
        }
        clearAllExceptItera(activity, 0);
    }

    /**
     * finish掉当前activity和之后的所有Activity
     * @param activity 当前
     * @param targetIndex
     */
    private static void clearAllExceptItera(Class<?> activity, int targetIndex) {
        //栈中只有一个Activity不处理
        if (mStack.size() <= 1) {
            return;
        }
        //指针不大于栈
        if (targetIndex >= mStack.size()) {
            return;
        }
        //找到当前Activity
        if (!mStack.get(targetIndex).getClass().equals(activity)) {
            mStack.get(targetIndex).finish();
        } else {
            targetIndex++;
        }
        //递归
        clearAllExceptItera(activity, targetIndex);
    }

    /**
     * finish掉栈中所有Activity（退出app）
     */
    public static void exit() {
        for (Activity activity : mStack) {
            try {
                activity.finish();
            } catch (Exception e) {
                e.printStackTrace();
                doException(e);
            }
        }
        mStack.clear();
    }

    /**
     * 获取任务栈长度
     * @return 任务栈长度
     */
    public static int size() {
        return mStack.size();
    }

    /**
     * 获取栈顶Activity（但不删除栈顶元素）
     * @return Activity对象
     */
    public static Activity getTop() {
        return mStack.peek();
    }

    /**
     * 查询栈中是否有activity对象
     * @param activity 要匹配的activity对象
     * @return 匹配结果activity，未找到返回null
     */
    public static Activity getActivity(Class<?> activity) {
        if (mStack == null || mStack.isEmpty()) {
            return null;
        }
        for (int i = mStack.size() - 1; i >= 0; i--) {
            try {
                Activity temp_activity = mStack.get(i);
                if (temp_activity.getClass().equals(activity)) {
                    return temp_activity;
                }
            } catch (Exception e) {
                e.printStackTrace();
                doException(e);
            }
        }
        return null;
    }

    /**
     * 判断当前Activity是否在栈顶
     * @param activity 判定Activity
     * @return 结果
     */
    public static boolean isTop(Class<?> activity) {
        Activity top = mStack.peek();
        if (top.getClass().equals(activity)) {
            return true;
        }
        return false;
    }

    /**
     * 处理错误日志
     * @param e 错误
     */
    private static void doException(Exception e) {
        Logger.e(TAG, "任务栈异常：" + e);
    }
}
