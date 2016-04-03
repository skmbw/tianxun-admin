package com.vteba.tianxun.index.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vteba.tianxun.user.service.spi.UserService;
import com.vteba.web.servlet.AutowiredHttpServlet;

/**
 * 首页
 * 
 * @author yinlei
 * @date 2015年3月24日 下午4:50:05
 */
@WebServlet(value = "/index")
public class IndexServlet extends AutowiredHttpServlet {
	private static final long serialVersionUID = 5865110934516570042L;
	private static final Logger LOGGER = LogManager.getLogger(IndexServlet.class);
	
	@Inject
	private UserService userServiceImpl;
	
	@Override
	public void servlet(HttpServletRequest request, HttpServletResponse response) {
		try {
			String userId = request.getParameter("userId");
			if (userId != null) {
				userServiceImpl.get(userId);
			}
			request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
		} catch (ServletException e) {
			LOGGER.error("Servlet Exception.", e);
		} catch (IOException e) {
			LOGGER.error("IO Exception.", e);
		}
	}

}
