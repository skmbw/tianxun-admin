package com.vteba.tianxun.answer.dao;

import com.vteba.tianxun.answer.model.AnswerImage;
import com.vteba.tx.jdbc.mybatis.annotation.DaoMapper;
import com.vteba.tx.jdbc.mybatis.spi.BasicDao;

/**
 * 问题回答图片表（answer_image）的 MyBatis DAO Mapper。<br>
 * 该类由代码工具自动生成，可以新增方法，但是不要修改自动生成的方法。如果修改请确保正确。
 * @author yinlei
 * @date 2016-03-26 20:34:43
 */
@DaoMapper
public interface AnswerImageDao extends BasicDao<AnswerImage, String> {
}