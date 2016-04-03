package com.vteba.tianxun.score.dao;

import com.vteba.tianxun.score.model.Score;
import com.vteba.tx.jdbc.mybatis.annotation.DaoMapper;
import com.vteba.tx.jdbc.mybatis.spi.BasicDao;

/**
 * 表score的MyBatis Dao Mapper。
 * 由代码工具自动生成，可以新增方法，但是不要修改自动生成的方法。
 * @date 2016-03-24 19:22:02
 */
@DaoMapper
public interface ScoreDao extends BasicDao<Score, String> {
}