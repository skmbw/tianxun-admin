package com.vteba.tianxun.question.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import com.vteba.tianxun.question.dao.CategoryDao;
import com.vteba.tianxun.question.model.Category;
import com.vteba.tianxun.question.service.spi.CategoryService;

import com.vteba.service.generic.impl.BasicServiceImpl;
import com.vteba.tx.jdbc.mybatis.spi.BasicDao;

/**
 * 问题分类相关的service业务实现。
 * @author yinlei
 * @date 2016-3-26 20:41:37
 */
@Named
public class CategoryServiceImpl extends BasicServiceImpl<Category, Integer> implements CategoryService {
	// 添加了方法后，记得改为private
	protected CategoryDao categoryDao;
	
	@Override
	@Inject
	public void setBasicDao(BasicDao<Category, Integer> categoryDao) {
		this.basicDao = categoryDao;
		this.categoryDao = (CategoryDao) categoryDao;
	}
}
