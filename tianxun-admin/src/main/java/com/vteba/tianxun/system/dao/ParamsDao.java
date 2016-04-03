package com.vteba.tianxun.system.dao;

import com.vteba.tianxun.system.model.Params;
import com.vteba.tx.jdbc.mybatis.annotation.DaoMapper;
import com.vteba.tx.jdbc.mybatis.spi.BasicDao;

/**
 * 系统参数表（params）的 MyBatis DAO Mapper。<br>
 * 该类由代码工具自动生成，可以新增方法，但是不要修改自动生成的方法。如果修改请确保正确。
 * @author yinlei
 * @date 2016-03-24 20:16:03
 */
@DaoMapper
public interface ParamsDao extends BasicDao<Params, String> {
}