package com.vteba.tianxun.system.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import com.vteba.tianxun.system.dao.ParamsDao;
import com.vteba.tianxun.system.model.Params;
import com.vteba.tianxun.system.service.spi.ParamsService;

import com.vteba.service.generic.impl.BasicServiceImpl;
import com.vteba.tx.jdbc.mybatis.spi.BasicDao;

/**
 * 系统参数表相关的service业务实现。
 * @author yinlei
 * @date 2016-3-24 20:16:02
 */
@Named
public class ParamsServiceImpl extends BasicServiceImpl<Params, String> implements ParamsService {
	// 添加了方法后，记得改为private
	protected ParamsDao paramsDao;
	
	@Override
	@Inject
	public void setBasicDao(BasicDao<Params, String> paramsDao) {
		this.basicDao = paramsDao;
		this.paramsDao = (ParamsDao) paramsDao;
	}
}
