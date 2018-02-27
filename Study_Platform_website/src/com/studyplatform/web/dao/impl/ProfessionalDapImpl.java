package com.studyplatform.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import com.studyplatform.web.bean.ProfessionBean;
import com.studyplatform.web.dao.ProfessionalDao;
import com.studyplatform.web.db.C3p0Utils;
import com.studyplatform.web.utils.DaoUtils;
import com.studyplatform.web.utils.DebugUtils;

/**
 * ProfessionalDap实现类
 * Title: ProfessionalDapImpl
 * @date 2018年2月27日
 * @author Freedom0013
 */
public class ProfessionalDapImpl implements ProfessionalDao {

    @Override
    public int addProfession(ProfessionBean professionbean) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int deleteProfession(int profession_id) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int updataProfession(ProfessionBean professionbean) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<ProfessionBean> getAllProfession() {
        Connection connection = null;
        PreparedStatement statement = null;
        List<ProfessionBean> professionList = null;
        ResultSet resultset = null;
        try {
            connection = C3p0Utils.getConnection();
            String sql = "SELECT * FROM `professions`";
            statement = connection.prepareStatement(sql);
            resultset = statement.executeQuery();
            professionList = new ArrayList<ProfessionBean>();
            while (resultset.next()) {
                ProfessionBean profession = new ProfessionBean();
                profession.setProfession_id(resultset.getInt(1));
                profession.setProfession_name(resultset.getString(2));
                profession.setProfession_picture_id(resultset.getBigDecimal(3));
                profession.setProfession_caption(resultset.getString(4));
                DateFormat ddtf = DateFormat.getDateTimeInstance();
                profession.setProfession_addtime(ddtf.format(resultset.getTimestamp(5))); 
                profession.setProfession_department_id(resultset.getInt(6));
//                DebugUtils.showLog(profession.toString());
                professionList.add(profession);
            }
        } catch (SQLException e) {
            DebugUtils.showLog(e.getMessage());
        } finally {
            DaoUtils.closeResource(connection,statement,resultset);
        }
        return professionList;
    }

    @Override
    public List<ProfessionBean> getAllProfessionByDepartmentId(int department_id) {
        Connection connection = null;
        PreparedStatement statement = null;
        List<ProfessionBean> professionList = null;
        ResultSet resultset = null;
        try {
            connection = C3p0Utils.getConnection();
            String sql = "SELECT * FROM `professions` WHERE profession_department_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, department_id);
            resultset = statement.executeQuery();
            professionList = new ArrayList<ProfessionBean>();
            while (resultset.next()) {
                ProfessionBean profession = new ProfessionBean();
                profession.setProfession_id(resultset.getInt(1));
                profession.setProfession_name(resultset.getString(2));
                profession.setProfession_picture_id(resultset.getBigDecimal(3));
                profession.setProfession_caption(resultset.getString(4));
                DateFormat ddtf = DateFormat.getDateTimeInstance();
                profession.setProfession_addtime(ddtf.format(resultset.getTimestamp(5))); 
                profession.setProfession_department_id(resultset.getInt(6));
//                DebugUtils.showLog(profession.toString());
                professionList.add(profession);
            }
        } catch (SQLException e) {
            DebugUtils.showLog(e.getMessage());
        } finally {
            DaoUtils.closeResource(connection,statement,resultset);
        }
        return professionList;
    }
    
    @Override
    public ProfessionBean getProfessionById(int profession_id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ProfessionBean profession = null;
        ResultSet resultset = null;
        try {
            connection = C3p0Utils.getConnection();
            String sql = "SELECT * FROM `professions` WHERE profession_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, profession_id);
            resultset = statement.executeQuery();
            while (resultset.next()) {
                profession = new ProfessionBean();
                profession.setProfession_id(resultset.getInt(1));
                profession.setProfession_name(resultset.getString(2));
                profession.setProfession_picture_id(resultset.getBigDecimal(3));
                profession.setProfession_caption(resultset.getString(4));
                DateFormat ddtf = DateFormat.getDateTimeInstance();
                profession.setProfession_addtime(ddtf.format(resultset.getTimestamp(5))); 
                profession.setProfession_department_id(resultset.getInt(6));
//                DebugUtils.showLog(profession.toString());
            }
        } catch (SQLException e) {
            DebugUtils.showLog(e.getMessage());
        } finally {
            DaoUtils.closeResource(connection,statement,resultset);
        }
        return profession;
    }
}
