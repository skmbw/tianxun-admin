package com.vteba.tianxun.question.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import com.vteba.tianxun.question.dao.TagsDao;
import com.vteba.tianxun.question.model.Tags;
import com.vteba.tianxun.question.service.spi.TagsService;

import com.vteba.service.generic.impl.BasicServiceImpl;
import com.vteba.tx.jdbc.mybatis.spi.BasicDao;

/**
 * 热门标签相关的service业务实现。
 * @author yinlei
 * @date 2016-3-26 20:41:37
 */
@Named
public class TagsServiceImpl extends BasicServiceImpl<Tags, String> implements TagsService {
	// 添加了方法后，记得改为private
	protected TagsDao tagsDao;
	
	@Override
	@Inject
	public void setBasicDao(BasicDao<Tags, String> tagsDao) {
		this.basicDao = tagsDao;
		this.tagsDao = (TagsDao) tagsDao;
	}
}
