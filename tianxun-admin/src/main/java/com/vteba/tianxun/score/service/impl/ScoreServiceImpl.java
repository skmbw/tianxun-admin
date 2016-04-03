package com.vteba.tianxun.score.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import com.vteba.tianxun.score.dao.ScoreDao;
import com.vteba.tianxun.score.model.Score;
import com.vteba.tianxun.score.service.spi.ScoreService;

import com.vteba.service.generic.impl.BasicServiceImpl;
import com.vteba.tx.jdbc.mybatis.spi.BasicDao;

/**
 * 用户积分相关的service业务实现。
 * @author yinlei
 * @date 2016-3-24 19:22:01
 */
@Named
public class ScoreServiceImpl extends BasicServiceImpl<Score, String> implements ScoreService {
	// 添加了方法后，记得改为private
	protected ScoreDao scoreDao;
	
	@Override
	@Inject
	public void setBasicDao(BasicDao<Score, String> scoreDao) {
		this.basicDao = scoreDao;
		this.scoreDao = (ScoreDao) scoreDao;
	}
}
