package com.vteba.tianxun.account.dao;

import com.vteba.tianxun.account.model.AccountDetail;
import com.vteba.tx.jdbc.mybatis.annotation.DaoMapper;
import com.vteba.tx.jdbc.mybatis.spi.BasicDao;

/**
 * 用户资金积分明细表（account_detail）的 MyBatis DAO Mapper。<br>
 * 该类由代码工具自动生成，可以新增方法，但是不要修改自动生成的方法。如果修改请确保正确。
 * @author yinlei
 * @date 2016-03-24 19:58:06
 */
@DaoMapper
public interface AccountDetailDao extends BasicDao<AccountDetail, String> {
}