package com.vteba.tianxun.account.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import com.vteba.tianxun.account.dao.PlatformDetailDao;
import com.vteba.tianxun.account.model.PlatformDetail;
import com.vteba.tianxun.account.service.spi.PlatformDetailService;

import com.vteba.service.generic.impl.BasicServiceImpl;
import com.vteba.tx.jdbc.mybatis.spi.BasicDao;

/**
 * 平台资金账户明细相关的service业务实现。
 * @author yinlei
 * @date 2016-3-24 19:58:05
 */
@Named
public class PlatformDetailServiceImpl extends BasicServiceImpl<PlatformDetail, String> implements PlatformDetailService {
	// 添加了方法后，记得改为private
	protected PlatformDetailDao platformDetailDao;
	
	@Override
	@Inject
	public void setBasicDao(BasicDao<PlatformDetail, String> platformDetailDao) {
		this.basicDao = platformDetailDao;
		this.platformDetailDao = (PlatformDetailDao) platformDetailDao;
	}
}
