package com.vteba.tianxun.utils.solr;

/**
 * solr查询结果bean，手动去构造。spring的solr封装的太过厚重。
 * 
 * @author yinlei
 * @date 2016年3月30日 下午4:11:53
 */
public class SolrBean {

	private String id; // id
	private String title; // 标题
	private String content; // 内容
	private String summary; // 摘要
	private String category; // 问题的分类
	private long version;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
}
