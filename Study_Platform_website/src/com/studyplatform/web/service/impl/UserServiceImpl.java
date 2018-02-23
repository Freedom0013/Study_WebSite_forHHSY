package com.studyplatform.web.service.impl;

import com.studyplatform.web.bean.UserBean;
import com.studyplatform.web.exception.UserExistException;
import com.studyplatform.web.service.UserService;

/**
 * 业务逻辑封装接口实现（用户登录） 
 * Title: UserServiceImpl
 * @date 2018年2月22日
 * @author Freedom0013
 */
public class UserServiceImpl implements UserService {

    @Override
    public UserBean login(String username, String password) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void register(UserBean user) throws UserExistException {
        // TODO Auto-generated method stub

    }

}
