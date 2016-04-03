package com.vteba.tianxun.account.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import com.vteba.tianxun.account.dao.AccountDetailDao;
import com.vteba.tianxun.account.model.AccountDetail;
import com.vteba.tianxun.account.service.spi.AccountDetailService;

import com.vteba.service.generic.impl.BasicServiceImpl;
import com.vteba.tx.jdbc.mybatis.spi.BasicDao;

/**
 * 用户资金积分明细相关的service业务实现。
 * @author yinlei
 * @date 2016-3-24 19:58:06
 */
@Named
public class AccountDetailServiceImpl extends BasicServiceImpl<AccountDetail, String> implements AccountDetailService {
	// 添加了方法后，记得改为private
	protected AccountDetailDao accountDetailDao;
	
	@Override
	@Inject
	public void setBasicDao(BasicDao<AccountDetail, String> accountDetailDao) {
		this.basicDao = accountDetailDao;
		this.accountDetailDao = (AccountDetailDao) accountDetailDao;
	}
}
