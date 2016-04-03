package com.vteba.tianxun.score.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import com.vteba.tianxun.score.dao.ScoreDetailDao;
import com.vteba.tianxun.score.model.ScoreDetail;
import com.vteba.tianxun.score.service.spi.ScoreDetailService;

import com.vteba.service.generic.impl.BasicServiceImpl;
import com.vteba.tx.jdbc.mybatis.spi.BasicDao;

/**
 * 积分明细相关的service业务实现。
 * @author yinlei
 * @date 2016-3-24 19:22:02
 */
@Named
public class ScoreDetailServiceImpl extends BasicServiceImpl<ScoreDetail, String> implements ScoreDetailService {
	// 添加了方法后，记得改为private
	protected ScoreDetailDao scoreDetailDao;
	
	@Override
	@Inject
	public void setBasicDao(BasicDao<ScoreDetail, String> scoreDetailDao) {
		this.basicDao = scoreDetailDao;
		this.scoreDetailDao = (ScoreDetailDao) scoreDetailDao;
	}
}
