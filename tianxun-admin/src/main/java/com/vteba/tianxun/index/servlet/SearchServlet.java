package com.vteba.tianxun.index.servlet;

import java.util.Map;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.SolrInputField;
import org.springframework.data.solr.core.SolrTemplate;

import com.google.common.collect.Maps;
import com.vteba.web.servlet.AutowiredHttpServlet;

/**
 * 搜索servlet。
 * 
 * @author yinlei
 * @date 2016年3月24日 下午8:31:31
 */
@WebServlet(value = "/search")
public class SearchServlet extends AutowiredHttpServlet {
	private static final long serialVersionUID = 5407795888008433996L;
	private static final Logger LOGGER = LogManager.getLogger(SearchServlet.class);
	private static final String WORD = "word";
	
	@Inject
	private SolrTemplate solrTemplate;
	
	@Inject
	private SolrClient solrClient;
	
	@Override
	public void servlet(HttpServletRequest request, HttpServletResponse response) {
		try {
			String word = request.getParameter(WORD);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(word);
			}
			
			Map<String, SolrInputField> fields = Maps.newHashMap();
			SolrInputField inputField = new SolrInputField("id");
			fields.put(word, inputField);
			SolrInputDocument document = new SolrInputDocument(fields);
			
			request.setAttribute(WORD, word);
			request.getRequestDispatcher("/WEB-INF/search.jsp").forward(request, response);
		} catch (Exception e) {
			LOGGER.error("发生搜索错误。", e);
		}
	}

}
