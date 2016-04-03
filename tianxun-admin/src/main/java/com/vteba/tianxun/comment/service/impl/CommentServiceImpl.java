package com.vteba.tianxun.comment.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import com.vteba.tianxun.comment.dao.CommentDao;
import com.vteba.tianxun.comment.model.Comment;
import com.vteba.tianxun.comment.service.spi.CommentService;

import com.vteba.service.generic.impl.BasicServiceImpl;
import com.vteba.tx.jdbc.mybatis.spi.BasicDao;

/**
 * 评论表相关的service业务实现。
 * @author yinlei
 * @date 2016-4-3 12:13:48
 */
@Named
public class CommentServiceImpl extends BasicServiceImpl<Comment, String> implements CommentService {
	// 添加了方法后，记得改为private
	protected CommentDao commentDao;
	
	@Override
	@Inject
	public void setBasicDao(BasicDao<Comment, String> commentDao) {
		this.basicDao = commentDao;
		this.commentDao = (CommentDao) commentDao;
	}
}
