package com.studyplatform.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.studyplatform.web.bean.OptionBean;
import com.studyplatform.web.bean.QuestionBean;
import com.studyplatform.web.dao.QuestionDao;
import com.studyplatform.web.db.C3p0Utils;
import com.studyplatform.web.system.SystemCommonValue;
import com.studyplatform.web.utils.DaoUtils;
import com.studyplatform.web.utils.DebugUtils;

/**
 * QuestionDao实现类
 * Title: QuestionDaoImpl
 * @date 2018年2月5日
 * @author Freedom0013
 */
public class QuestionDaoImpl implements QuestionDao {
    @Override
    public int addExamQuestion(QuestionBean question) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = C3p0Utils.getConnection();
            String sql = "INSERT INTO questions (question_stem,question_option_id,question_level,"
                    + "question_answer,question_analysis,question_type,question_addtime,"
                    + "question_course_id,question_chapter) " + "VALUES ('"
                    + question.getQuestion_stem() + "','" 
                    + question.getQuestion_option_id() + "','"
                    + question.getQuestion_level() + "','" 
                    + question.getQuestion_answer() + "','" 
                    + question.getQuestion_analysis() + "','" 
                    + question.getQuestion_type() + "','" 
                    + question.getQuestion_addtime() + "','" 
                    + question.getQuestion_course_id() + "','"
                    + question.getQuestion_chapter() + "')";
            DebugUtils.showLog("添加语句：" + sql);
            statement = connection.prepareStatement(sql);
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return SystemCommonValue.OPERATION_FAILED;
        } catch (Exception e) {
            e.printStackTrace();
            return SystemCommonValue.OPERATION_FAILED;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return SystemCommonValue.OPERATION_FAILED;
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return SystemCommonValue.OPERATION_FAILED;
                }
            }
        }
        return SystemCommonValue.OPERATION_SUCCESS;
    }

    @Override
    public ArrayList<QuestionBean> ObtainExaminationList(int course_id, int Easy, int nomal, int hard) {
        Connection connection = null;
        PreparedStatement statement = null;
        ArrayList<QuestionBean> questions = null;
        ResultSet resultset = null;
        try {
            connection = C3p0Utils.getConnection();
            //简单题查询
            String sql = "SELECT * FROM questions AS A , `options` AS B WHERE A.question_option_id = B.option_id AND A.question_level = ? AND A.question_course_id = ? ORDER BY RAND() LIMIT ? ";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, SystemCommonValue.EXAM_QUESTION_EASY_VALUE);
            statement.setInt(2, course_id);
            statement.setInt(3, Easy);
            resultset = statement.executeQuery();
            questions = new ArrayList<QuestionBean>();            
            while (resultset.next()) {
                QuestionBean question_easy = new QuestionBean();
                question_easy.setQuestion_id(resultset.getBigDecimal(1));
                question_easy.setQuestion_stem(resultset.getString(2));
                question_easy.setQuestion_option_id(resultset.getBigDecimal(3));
                question_easy.setQuestion_level(resultset.getInt(4));
                question_easy.setQuestion_answer(resultset.getString(5));
                question_easy.setQuestion_analysis(resultset.getString(6));
                question_easy.setQuestion_type(resultset.getInt(7));
                //毫秒格式化
                DateFormat ddtf = DateFormat.getDateTimeInstance();
                question_easy.setQuestion_addtime(ddtf.format(resultset.getTimestamp(8)));
//                question_easy.setQuestion_addtime(resultset.getString(8));
                question_easy.setQuestion_course_id(resultset.getInt(9));
                question_easy.setQuestion_chapter(resultset.getString(10));
                OptionBean option_easy = new OptionBean();
                option_easy.setOption_id(resultset.getBigDecimal(11));
                option_easy.setOption_a(resultset.getString(12));
                option_easy.setOption_b(resultset.getString(13));
                option_easy.setOption_c(resultset.getString(14));
                option_easy.setOption_d(resultset.getString(15));
                if(question_easy.getQuestion_type() == SystemCommonValue.EXAM_QUESTION_TYPE_MULTI){
                    option_easy.setOption_e(resultset.getString(16));
                    option_easy.setOption_f(resultset.getString(17));
                    option_easy.setOption_g(resultset.getString(18));
                }
                question_easy.setOption(option_easy);
//                DebugUtils.showLog("QuestionBean:"+question.toString());
                questions.add(question_easy);
            }
            //中等题查询
            sql = "SELECT * FROM questions AS A , `options` AS B WHERE A.question_option_id = B.option_id AND A.question_level = ? AND A.question_course_id = ? ORDER BY RAND() LIMIT ? ";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, SystemCommonValue.EXAM_QUESTION_NOMAL_VALUE);
            statement.setInt(2, course_id);
            statement.setInt(3, nomal);
            resultset = statement.executeQuery();
            while (resultset.next()) {
                QuestionBean question_nomal = new QuestionBean();
                question_nomal.setQuestion_id(resultset.getBigDecimal(1));
                question_nomal.setQuestion_stem(resultset.getString(2));
                question_nomal.setQuestion_option_id(resultset.getBigDecimal(3));
                question_nomal.setQuestion_level(resultset.getInt(4));
                question_nomal.setQuestion_answer(resultset.getString(5));
                question_nomal.setQuestion_analysis(resultset.getString(6));
                question_nomal.setQuestion_type(resultset.getInt(7));
                //毫秒格式化
                DateFormat ddtf = DateFormat.getDateTimeInstance();
                question_nomal.setQuestion_addtime(ddtf.format(resultset.getTimestamp(8)));
                question_nomal.setQuestion_course_id(resultset.getInt(9));
                question_nomal.setQuestion_chapter(resultset.getString(10));
                OptionBean option_nomal = new OptionBean();
                option_nomal.setOption_id(resultset.getBigDecimal(11));
                option_nomal.setOption_a(resultset.getString(12));
                option_nomal.setOption_b(resultset.getString(13));
                option_nomal.setOption_c(resultset.getString(14));
                option_nomal.setOption_d(resultset.getString(15));
                if (question_nomal.getQuestion_type() == SystemCommonValue.EXAM_QUESTION_TYPE_MULTI) {
                    option_nomal.setOption_e(resultset.getString(16));
                    option_nomal.setOption_f(resultset.getString(17));
                    option_nomal.setOption_g(resultset.getString(18));
                }
                question_nomal.setOption(option_nomal);
                // DebugUtils.showLog("QuestionBean:"+question.toString());
                questions.add(question_nomal);
            }
            //困难题查询
            sql = "SELECT * FROM questions AS A , `options` AS B WHERE A.question_option_id = B.option_id AND A.question_level = ? AND A.question_course_id = ? ORDER BY RAND() LIMIT ? ";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, SystemCommonValue.EXAM_QUESTION_HARD_VALUE);
            statement.setInt(2, course_id);
            statement.setInt(3, hard);
            resultset = statement.executeQuery();
            while (resultset.next()) {
                QuestionBean question_hard = new QuestionBean();
                question_hard.setQuestion_id(resultset.getBigDecimal(1));
                question_hard.setQuestion_stem(resultset.getString(2));
                question_hard.setQuestion_option_id(resultset.getBigDecimal(3));
                question_hard.setQuestion_level(resultset.getInt(4));
                question_hard.setQuestion_answer(resultset.getString(5));
                question_hard.setQuestion_analysis(resultset.getString(6));
                question_hard.setQuestion_type(resultset.getInt(7));
                //毫秒格式化
                DateFormat ddtf = DateFormat.getDateTimeInstance();
                question_hard.setQuestion_addtime(ddtf.format(resultset.getTimestamp(8)));
                question_hard.setQuestion_course_id(resultset.getInt(9));
                question_hard.setQuestion_chapter(resultset.getString(10));
                OptionBean option_hard = new OptionBean();
                option_hard.setOption_id(resultset.getBigDecimal(11));
                option_hard.setOption_a(resultset.getString(12));
                option_hard.setOption_b(resultset.getString(13));
                option_hard.setOption_c(resultset.getString(14));
                option_hard.setOption_d(resultset.getString(15));
                if (question_hard.getQuestion_type() == SystemCommonValue.EXAM_QUESTION_TYPE_MULTI) {
                    option_hard.setOption_e(resultset.getString(16));
                    option_hard.setOption_f(resultset.getString(17));
                    option_hard.setOption_g(resultset.getString(18));
                }
                question_hard.setOption(option_hard);
                // DebugUtils.showLog("QuestionBean:"+question.toString());
                questions.add(question_hard);
            }
            DebugUtils.showLog("试卷集合长度：" + questions.size());
        } catch (SQLException e) {
            DebugUtils.showLog(e.getMessage());
        } finally {
           DaoUtils.closeResource(connection,statement,resultset);
        }
        return questions;
    }

    @Override
    public ArrayList<QuestionBean> ObtainOfficialExam(int course_id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<QuestionBean> ObtainUsersExam(int course_id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int UpdataQuestion(QuestionBean question) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int DeleteQuestion(int question_id) {
        // TODO Auto-generated method stub
        return 0;
    }
}
