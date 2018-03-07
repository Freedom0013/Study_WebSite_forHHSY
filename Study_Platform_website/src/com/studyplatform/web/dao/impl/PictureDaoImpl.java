package com.studyplatform.web.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;

import com.studyplatform.web.bean.PictureBean;
import com.studyplatform.web.dao.PictureDao;
import com.studyplatform.web.db.C3p0Utils;
import com.studyplatform.web.utils.DaoUtils;
import com.studyplatform.web.utils.DebugUtils;

/**
 * PictureDao实现类
 * Title: PictureDaoImpl
 * @date 2018年3月7日
 * @author Freedom0013
 */
public class PictureDaoImpl implements PictureDao {
    @Override
    public int addPicture(PictureBean picture) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public PictureBean getPictureById(BigDecimal picture_id) {
        Connection connection = null;
        PreparedStatement statement = null;
        PictureBean picture = null;
        ResultSet resultset = null;
        try {
            connection = C3p0Utils.getConnection();
            String sql = "SELECT * FROM pictures WHERE picture_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setBigDecimal(1, picture_id);
            resultset = statement.executeQuery();
            while (resultset.next()) {
                picture = new PictureBean();
                picture.setPicture_id(resultset.getBigDecimal(1));
                picture.setPicture_name(resultset.getString(2));
                picture.setPicture_caption(resultset.getString(3));
                picture.setPicture_img(resultset.getString(4));
                DateFormat ddtf = DateFormat.getDateTimeInstance();
                picture.setPicture_addtime(ddtf.format(resultset.getTimestamp(5)));
            }
        } catch (SQLException e) {
            DebugUtils.showLog(e.getMessage());
        } finally {
            DaoUtils.closeResource(connection,statement,resultset);
        }
        return picture;
    }

    @Override
    public String getPicrureAddressById(BigDecimal picture_id) {
        Connection connection = null;
        PreparedStatement statement = null;
        String picture_url = null;
        ResultSet resultset = null;
        try {
            connection = C3p0Utils.getConnection();
            String sql = "SELECT picture_img FROM pictures WHERE picture_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setBigDecimal(1, picture_id);
            resultset = statement.executeQuery();
            while (resultset.next()) {
                picture_url = resultset.getString(1);
            }
        } catch (SQLException e) {
            DebugUtils.showLog(e.getMessage());
        } finally {
            DaoUtils.closeResource(connection,statement,resultset);
        }
        return picture_url;
    }

    @Override
    public int UpdataPicture(PictureBean picture) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int DeletePicture(BigDecimal picture_id) {
        // TODO Auto-generated method stub
        return 0;
    }

}
