package com.studyplatform.web.service.impl;

import java.util.List;

import com.studyplatform.web.bean.ResourceBean;
import com.studyplatform.web.dao.ResourceDao;
import com.studyplatform.web.dao.impl.ResourceDaoImpl;
import com.studyplatform.web.service.ResourceService;

/**
 * 业务逻辑封装接口实现（资源操作） 
 * Title: ResourceServiceImpl
 * @date 2018年3月5日
 * @author Freedom0013
 */
public class ResourceServiceImpl implements ResourceService {
    ResourceDao dao = new ResourceDaoImpl();
    
    @Override
    public List<ResourceBean> getResourceByDegree(int resource_degree, int course_id) {
        return dao.getAllbyDegree(resource_degree, course_id);
    }

    @Override
    public List<ResourceBean> getAllResourceListByPage(int page_Num, int page_Size) {
        return dao.getAllResourceListByPage(page_Num, page_Size);
    }

    @Override
    public ResourceBean getResourceById(int resource_id) {
        return dao.getResourceById(resource_id);
    }

    @Override
    public List<ResourceBean> getResourceByCourseId(int course_id) {
        return dao.getAllResourceByCourseId(course_id);
    }
}
