package com.vteba.tianxun.answer.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import com.vteba.tianxun.answer.dao.AnswerDao;
import com.vteba.tianxun.answer.model.Answer;
import com.vteba.tianxun.answer.service.spi.AnswerService;

import com.vteba.service.generic.impl.BasicServiceImpl;
import com.vteba.tx.jdbc.mybatis.spi.BasicDao;

/**
 * 问题回答表相关的service业务实现。
 * @author yinlei
 * @date 2016-3-26 20:34:40
 */
@Named
public class AnswerServiceImpl extends BasicServiceImpl<Answer, String> implements AnswerService {
	// 添加了方法后，记得改为private
	protected AnswerDao answerDao;
	
	@Override
	@Inject
	public void setBasicDao(BasicDao<Answer, String> answerDao) {
		this.basicDao = answerDao;
		this.answerDao = (AnswerDao) answerDao;
	}
}
