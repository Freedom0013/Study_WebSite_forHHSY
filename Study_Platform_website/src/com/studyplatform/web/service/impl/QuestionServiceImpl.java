package com.studyplatform.web.service.impl;

import java.util.List;

import com.studyplatform.web.bean.QuestionBean;
import com.studyplatform.web.dao.QuestionDao;
import com.studyplatform.web.dao.impl.QuestionDaoImpl;
import com.studyplatform.web.service.QuestionService;

/**
 * 业务逻辑封装接口实现（试题操作） 
 * Title: QuestionServiceImpl
 * @date 2018年2月22日
 * @author Freedom0013
 */
public class QuestionServiceImpl implements QuestionService {
    QuestionDao dao = new QuestionDaoImpl();
    
    @Override
    public List<QuestionBean> getRandomExaminationByCourse(int course_id) {
        return dao.ObtainExaminationList(course_id, 4, 3, 3);
    }

    @Override
    public List<QuestionBean> getRandomExaminationByCourseAndDiff(int course_id, int Easy, int nomal, int hard) {
        return dao.ObtainExaminationList(course_id, Easy, nomal, hard);
    }
}
