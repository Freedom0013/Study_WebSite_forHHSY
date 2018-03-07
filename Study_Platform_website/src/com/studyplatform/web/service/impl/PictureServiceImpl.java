package com.studyplatform.web.service.impl;

import java.math.BigDecimal;

import com.studyplatform.web.bean.PictureBean;
import com.studyplatform.web.dao.PictureDao;
import com.studyplatform.web.dao.impl.PictureDaoImpl;
import com.studyplatform.web.service.PictureService;

/**
 * 业务逻辑封装接口实现（图片操作） 
 * Title: PictureServiceImpl
 * @date 2018年3月7日
 * @author Freedom0013
 */
public class PictureServiceImpl implements PictureService {
    
    PictureDao dao = new PictureDaoImpl();
    
    @Override
    public int addPicture(PictureBean picture) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public PictureBean getPictureById(BigDecimal picture_id) {
        return dao.getPictureById(picture_id);
    }

    @Override
    public String getPicrureAddressById(BigDecimal picture_id) {
        return dao.getPicrureAddressById(picture_id);
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
