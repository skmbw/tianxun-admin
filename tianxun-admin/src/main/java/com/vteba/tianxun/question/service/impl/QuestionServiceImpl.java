package com.vteba.tianxun.question.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import com.vteba.tianxun.question.dao.QuestionDao;
import com.vteba.tianxun.question.model.Question;
import com.vteba.tianxun.question.service.spi.QuestionService;

import com.vteba.service.generic.impl.BasicServiceImpl;
import com.vteba.tx.jdbc.mybatis.spi.BasicDao;

/**
 * 问题表相关的service业务实现。
 * @author yinlei
 * @date 2016-3-26 20:41:36
 */
@Named
public class QuestionServiceImpl extends BasicServiceImpl<Question, String> implements QuestionService {
	// 添加了方法后，记得改为private
	protected QuestionDao questionDao;
	
	@Override
	@Inject
	public void setBasicDao(BasicDao<Question, String> questionDao) {
		this.basicDao = questionDao;
		this.questionDao = (QuestionDao) questionDao;
	}
}
