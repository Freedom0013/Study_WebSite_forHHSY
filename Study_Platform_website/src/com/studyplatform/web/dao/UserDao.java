package com.studyplatform.web.dao;

import com.studyplatform.web.bean.UserBean;

/**
 * UserDao接口（数据库处理层）
 * Title: UserDao
 * @date 2018年2月1日
 * @author Freedom0013
 */
public interface UserDao {
    /*
     * 设置此接口的目的是提高程序可扩展性和内聚性，降低与其他模块的耦合性
     * 并且在后期添加功能时直接再此添加，实现类增加实现即可调用、修改方便
     */
    /**
     * 注册用户
     * @param user 要注册的用户
     */
    public int add(UserBean user) ;
    
    /**
     * 根据用户名和密码查询用户（用户登录）
     * @param username 用户名
     * @param password 密码
     * @return 查询到用户返回此用户，否则返回null
     */
    public UserBean findUserByUserNameAndPassword(String username,String password) ;
    
    /**
     * 根据用户的名字查找用户（判断是否用户存在）
     * @param name 用户的名字
     * @return 查询到了返回此用户，否则返回null
     */
    public UserBean findUserByUserName(String name) ;
    
    /**
     * 更新用户信息
     * @param user 需要被更新的user
     * @return 更新是否成功
     */
    public int UpdataUser(UserBean user);
}
