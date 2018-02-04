package com.studyplatform.web.dao;

import java.util.ArrayList;

import com.studyplatform.web.bean.QuestionBean;

/**
 * QuestionDao试题操作接口
 * Title: QuestionDao
 * @date 2018年2月2日
 * @author Freedom0013
 */
public interface QuestionDao {
    /**
     * 新增试题接口
     * @param question 试题Bean
     * @return 返回状态码
     */
    public int addQuestion(QuestionBean question);
    
    /**
     * 随机获取对应科目试卷（自动组卷）
     * @param course_id 课程id
     * @return 试题List
     */
    public ArrayList<QuestionBean> ObtainExaminationList(int course_id);
}
