package com.studyplatform.web.service;

import java.util.List;

import com.studyplatform.web.bean.ProfessionBean;

/**
 * 业务逻辑封装接口（专业列表）
 * Title: ProfessionalService
 * @date 2018年2月22日
 * @author Freedom0013
 */
public interface ProfessionalService {
    /**
     * 根据院系id获取对应专业列表
     * @param department_id 院系id
     * @return 院系所属专业列表
     */
    List<ProfessionBean> getAllProfessionsByDepId(int department_id);
}
