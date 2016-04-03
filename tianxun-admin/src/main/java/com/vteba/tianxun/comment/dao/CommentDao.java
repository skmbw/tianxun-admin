package com.vteba.tianxun.comment.dao;

import com.vteba.tianxun.comment.model.Comment;
import com.vteba.tx.jdbc.mybatis.annotation.DaoMapper;
import com.vteba.tx.jdbc.mybatis.spi.BasicDao;

/**
 * 评论表（comment）的 MyBatis DAO Mapper。<br>
 * 该类由代码工具自动生成，可以新增方法，但是不要修改自动生成的方法。如果修改请确保正确。
 * @author yinlei
 * @date 2016-04-03 12:13:55
 */
@DaoMapper
public interface CommentDao extends BasicDao<Comment, String> {
}