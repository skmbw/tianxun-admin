package com.vteba.tianxun.user.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import com.vteba.tianxun.user.dao.UserDao;
import com.vteba.tianxun.user.model.User;
import com.vteba.tianxun.user.service.spi.UserService;

import com.vteba.service.generic.impl.BasicServiceImpl;
import com.vteba.tx.jdbc.mybatis.spi.BasicDao;

/**
 * 用户表相关的service业务实现。
 * @author yinlei
 * @date 2016-3-24 20:14:12
 */
@Named
public class UserServiceImpl extends BasicServiceImpl<User, String> implements UserService {
	// 添加了方法后，记得改为private
	protected UserDao userDao;
	
	@Override
	@Inject
	public void setBasicDao(BasicDao<User, String> userDao) {
		this.basicDao = userDao;
		this.userDao = (UserDao) userDao;
	}
}
