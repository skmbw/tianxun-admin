package com.vteba.tianxun.utils.common;

import javax.servlet.http.HttpSession;

import com.vteba.common.constant.CommonConst;
import com.vteba.common.exception.BasicException;
import com.vteba.tianxun.user.model.User;
import com.vteba.utils.web.RequestContextHolder;

/**
 * 用户工具类，对从Action过来的请求可以，对后台业务不适用。
 * @author yinlei
 * @date 2016-3-27 20:50
 */
public class UserUtils {
	
	/**
	 * 获取当前登录的用户。集群部署的话，这个要调整。
	 * @return 用户
	 */
	public static User getUser() {
		HttpSession session = RequestContextHolder.getSession();
		if (session == null) {
			throw new BasicException("Current thread's HttpSession cannot be null");
		}
		User user = (User) session.getAttribute(CommonConst.CONTEXT_USER);
		if (user == null) {
			throw new BasicException("User not login");
		}
		return user;
	}
	
	/**
	 * 获取用户id
	 * @return
	 */
	public static String getUserId() {
		return getUser().getId();
	}
	
	/**
	 * 获取用户账号
	 * @return
	 */
	public static String getAccount() {
		return getUser().getAccount();
	}
	
	/**
	 * 获取用户手机号码
	 * @return
	 */
	public static String getMobile() {
		return getUser().getMobile();
	}
}
