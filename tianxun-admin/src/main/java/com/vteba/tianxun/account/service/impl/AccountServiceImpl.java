package com.vteba.tianxun.account.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import com.vteba.tianxun.account.dao.AccountDao;
import com.vteba.tianxun.account.model.Account;
import com.vteba.tianxun.account.service.spi.AccountService;

import com.vteba.service.generic.impl.BasicServiceImpl;
import com.vteba.tx.jdbc.mybatis.spi.BasicDao;

/**
 * 用户资金账户表相关的service业务实现。
 * @author yinlei
 * @date 2016-3-24 19:58:06
 */
@Named
public class AccountServiceImpl extends BasicServiceImpl<Account, String> implements AccountService {
	// 添加了方法后，记得改为private
	protected AccountDao accountDao;
	
	@Override
	@Inject
	public void setBasicDao(BasicDao<Account, String> accountDao) {
		this.basicDao = accountDao;
		this.accountDao = (AccountDao) accountDao;
	}
}
