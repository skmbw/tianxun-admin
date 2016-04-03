package com.vteba.tianxun.answer.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import com.vteba.tianxun.answer.dao.AnswerImageDao;
import com.vteba.tianxun.answer.model.AnswerImage;
import com.vteba.tianxun.answer.service.spi.AnswerImageService;

import com.vteba.service.generic.impl.BasicServiceImpl;
import com.vteba.tx.jdbc.mybatis.spi.BasicDao;

/**
 * 问题回答图片表相关的service业务实现。
 * @author yinlei
 * @date 2016-3-26 20:34:42
 */
@Named
public class AnswerImageServiceImpl extends BasicServiceImpl<AnswerImage, String> implements AnswerImageService {
	// 添加了方法后，记得改为private
	protected AnswerImageDao answerImageDao;
	
	@Override
	@Inject
	public void setBasicDao(BasicDao<AnswerImage, String> answerImageDao) {
		this.basicDao = answerImageDao;
		this.answerImageDao = (AnswerImageDao) answerImageDao;
	}
}
