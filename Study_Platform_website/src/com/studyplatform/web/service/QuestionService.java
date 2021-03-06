package com.studyplatform.web.service;

import java.math.BigDecimal;
import java.util.List;

import com.studyplatform.web.bean.QuestionBean;

/**
 * 业务逻辑封装接口（试题服务）
 * Title: QuestionService
 * @date 2018年2月22日
 * @author Freedom0013
 */
public interface QuestionService {
    /**
     * 根据课程id获取课程对应的随机试题（默认难度--简单：中等：难题==4:3:3）
     * @param course_id 课程id
     * @return 十道随机试题列表
     */
    List<QuestionBean> getRandomExaminationByCourse(int course_id);
    
    /**
     * 根据课程id获取课程对应的自定义难度比例的随机试题
     * @param course_id 课程id
     * @param Easy 简单题比例
     * @param nomal 中等题比例
     * @param hard 困难题比例
     * @return 十道对应难度随机试题列表
     */
    List<QuestionBean> getRandomExaminationByCourseAndDiff(int course_id, int Easy, int nomal, int hard);

    /**
     * 根据试题id查找试题详细信息
     * @param question_id 试题id
     * @return 试题详情
     */
    QuestionBean getQuestionById(BigDecimal question_id);
}
