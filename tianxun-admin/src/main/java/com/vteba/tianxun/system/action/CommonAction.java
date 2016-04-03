package com.vteba.tianxun.system.action;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vteba.common.constant.CommonConst;
import com.vteba.tianxun.user.model.User;
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
