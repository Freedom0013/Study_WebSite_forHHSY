package com.studyplatform.web.dao;

import java.util.ArrayList;

import com.studyplatform.web.bean.QuestionBean;

/**
 * QuestionDao（试题数据处理层接口）
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
    int addExamQuestion(QuestionBean question);
    
    /**
     * 随机获取对应科目试题（简单随机组卷）（开始答题逻辑）
     * @param course_id 课程id
     * @param Easy 简单题数量
     * @param nomal 中等题数量
     * @param hard 困难题数量
     * @return 试题List
     */
    ArrayList<QuestionBean> ObtainExaminationList(int course_id,int Easy,int nomal,int hard);
    
    /**
     * 获取官方组卷试题
     * @param course_id 科目id
     * @return 试题List
     */
    ArrayList<QuestionBean> ObtainOfficialExam(int course_id);
    
    /**
     * 获取用户组卷试题
     * @param course_id 科目id
     * @return 试题List
     */
    ArrayList<QuestionBean> ObtainUsersExam(int course_id);
    
    /**
     * 修改试题
     * @param question 试题Bean
     * @return 修改状态码
     */
    int UpdataQuestion(QuestionBean question);
    
    /**
     * 删除试题（待定）
     * @param question 试题id
     * @return 修改状态码
     */
    int DeleteQuestion(int question_id);
}
