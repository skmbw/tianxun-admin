package com.vteba.tianxun.system.action;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vteba.common.constant.CommonConst;
import com.vteba.tianxun.user.model.User;
import com.vteba.tianxun.utils.common.UserUtils;
import com.vteba.utils.common.RandomNumber;
import com.vteba.web.action.GenericAction;

/**
 * 杂七杂八的业务的action
 * @author yinlei
 * @date 2015年7月5日 下午2:27:06
 */
@Controller
@RequestMapping("/common")
public class CommonAction extends GenericAction<User> {
	
	@RequestMapping("/platform")
	public String platofrm() {
		User user = UserUtils.getUser();
		if (user != null) {
			setAttributeToSession(CommonConst.CONTEXT_USER, user);
			
			// 登录成功，将账号和机构代码，放入cookie中
			// 集群状态下同步用户信息是需要的，将状态放在客户端
			String account = "";
			try {
				account = URLEncoder.encode(user.getAccount(), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// ignore
			}
			Cookie cookie = new Cookie(CommonConst.SEC_ACCOUNT, account);
			cookie.setMaxAge(Integer.MAX_VALUE);
			cookie.setPath("/");
			getHttpServletResponse().addCookie(cookie);
			
//			cookie = new Cookie(CommonConst.SEC_TENANT, user.getOrgCode());
//			cookie.setMaxAge(Integer.MAX_VALUE);
//			cookie.setPath("/");
//			getHttpServletResponse().addCookie(cookie);
//			// 如果有首页的统计权限，显示统计
//			DefaultGrantedAuthority authority = new DefaultGrantedAuthority("平台总览", user.getOrgId());
//			if (user.getAuthorities().contains(authority)) {
//				setAttributeToSession(INDEX_STATS, true);
//				return "redirect:/indexStats/show";
//			} else {
//				// 没有统计权限，显示普通页
//				setAttributeToSession(INDEX_STATS, false);
//				return "common/platform";
//			}
			return "common/platform";
		} else { // 没有获取到用户信息，调到登录页
			return "redirect:/employee/login";
		}
	}
	
	@RequestMapping("/sessionTimeout")
	public String sessionTimeout() {
		// 记住用户名
		Cookie[] cookies = getHttpServletRequest().getCookies();
		for (Cookie cookie : cookies) {
			String name = cookie.getName();
			if (name.equals(CommonConst.SEC_ACCOUNT)) {
				String account = "";
				try {
					account = URLDecoder.decode(cookie.getValue(), "UTF-8");
				} catch (UnsupportedEncodingException e) {
					// ignore
				}
				setAttributeToRequest(CommonConst.SEC_ACCOUNT, account);
			} else if (name.equals(CommonConst.SEC_TENANT)) {
				setAttributeToRequest(CommonConst.SEC_TENANT, cookie.getValue());
			}
		}
		return "login"; // session超时，到登录页面
	}
	
	@RequestMapping("/accessDenied")
	public String accessDenied() {
		return "accessDenied";
	}
	
	@RequestMapping("/random")
	public void random(HttpServletResponse response) throws Exception {
		RandomNumber randomNum = RandomNumber.get();
		String authCode = randomNum.getString().toLowerCase();
		// 取得随机字符串放入HttpSession
		getHttpSession().setAttribute("random", authCode);
		OutputStream os = response.getOutputStream();
		response.setContentType("image/JPEG");
		// 将验证码写入输出流
		os.write(randomNum.getImageByteArray());
		os.flush();
	}
}
