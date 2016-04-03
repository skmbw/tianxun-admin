package com.vteba.tianxun.question.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import com.vteba.tianxun.question.dao.QuestionImageDao;
import com.vteba.tianxun.question.model.QuestionImage;
import com.vteba.tianxun.question.service.spi.QuestionImageService;

import com.vteba.service.generic.impl.BasicServiceImpl;
import com.vteba.tx.jdbc.mybatis.spi.BasicDao;

/**
 * 问题图片表相关的service业务实现。
 * @author yinlei
 * @date 2016-3-26 20:41:37
 */
@Named
public class QuestionImageServiceImpl extends BasicServiceImpl<QuestionImage, String> implements QuestionImageService {
	// 添加了方法后，记得改为private
	protected QuestionImageDao questionImageDao;
	
	@Override
	@Inject
	public void setBasicDao(BasicDao<QuestionImage, String> questionImageDao) {
		this.basicDao = questionImageDao;
		this.questionImageDao = (QuestionImageDao) questionImageDao;
	}
}
