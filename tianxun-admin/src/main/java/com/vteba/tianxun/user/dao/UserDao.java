package com.vteba.tianxun.user.dao;

import com.vteba.tianxun.user.model.User;
import com.vteba.tx.jdbc.mybatis.annotation.DaoMapper;
import com.vteba.tx.jdbc.mybatis.spi.BasicDao;

/**
 * 用户表（user）的 MyBatis DAO Mapper。<br>
 * 该类由代码工具自动生成，可以新增方法，但是不要修改自动生成的方法。如果修改请确保正确。
 * @author yinlei
 * @date 2016-03-24 20:14:14
 */
@DaoMapper
public interface UserDao extends BasicDao<User, String> {
}