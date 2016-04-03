package com.vteba.tianxun.account.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import com.vteba.tianxun.account.dao.PlatformAccountDao;
import com.vteba.tianxun.account.model.PlatformAccount;
import com.vteba.tianxun.account.service.spi.PlatformAccountService;

import com.vteba.service.generic.impl.BasicServiceImpl;
import com.vteba.tx.jdbc.mybatis.spi.BasicDao;

/**
 * 平台资金账户相关的service业务实现。
 * @author yinlei
 * @date 2016-3-24 19:58:06
 */
@Named
public class PlatformAccountServiceImpl extends BasicServiceImpl<PlatformAccount, String> implements PlatformAccountService {
	// 添加了方法后，记得改为private
	protected PlatformAccountDao platformAccountDao;
	
	@Override
	@Inject
	public void setBasicDao(BasicDao<PlatformAccount, String> platformAccountDao) {
		this.basicDao = platformAccountDao;
		this.platformAccountDao = (PlatformAccountDao) platformAccountDao;
	}
}
