package com.vteba.tianxun.index.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vteba.tianxun.index.model.Article;
import com.vteba.web.action.GenericAction;

/**
 * 首页文章action
 * @author yinlei
 * @date 2016-3-26 21:13
 */
@Controller
@RequestMapping("/article")
public class ArticleAction extends GenericAction<Article> {
	private static final Logger LOGGER = LogManager.getLogger(ArticleAction.class);
	/**
	 * 文章明细
	 * @param article 参数
	 * @return 明细页
	 */
	@RequestMapping("/detail/{questionId}")
	public String detail(Article article) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("questionId=[{}].", article.getQuestionId());
		}
		return "article/detail";
	}
}
