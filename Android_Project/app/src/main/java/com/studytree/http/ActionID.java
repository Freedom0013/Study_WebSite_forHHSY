package com.studytree.http;

/**
 * 接口码类
 * Title: ActionID
 * @date 2018/6/20 23:15
 * @author Freedom0013
 */
public interface ActionID {
    /** 接口码基数 */
    int BASE = 0x80000000;

    //1.3
    int ACTION_INIT = BASE + 1000;
    int ACTION_COURSE = BASE + 1001;
    int ACTION_COURSE_DETAIL = BASE + 1002;
    int ACTION_DEPARTMENT = BASE + 1003;
    int ACTION_EXAMINATION = BASE + 1004;
    int ACTION_LOGIN = BASE + 1005;
    int ACTION_PICTURE = BASE + 1006;
    int ACTION_PROFESSIONAL = BASE + 1007;
    int ACTION_QUESTION = BASE + 1008;
    int ACTION_REGISTER = BASE + 1009;
    int ACTION_WRITTEN_OFF = BASE + 1010;
    int ACTION_ANSWER = BASE + 1011;
    int ACTION_BANNER = BASE + 1012;
}
