package com.vteba.tianxun.question.dao;

import com.vteba.tianxun.question.model.Category;
import com.vteba.tx.jdbc.mybatis.annotation.DaoMapper;
import com.vteba.tx.jdbc.mybatis.spi.BasicDao;

/**
 * 问题分类表（category）的 MyBatis DAO Mapper。<br>
 * 该类由代码工具自动生成，可以新增方法，但是不要修改自动生成的方法。如果修改请确保正确。
 * @author yinlei
 * @date 2016-03-26 20:41:37
 */
@DaoMapper
public interface CategoryDao extends BasicDao<Category, Integer> {
}