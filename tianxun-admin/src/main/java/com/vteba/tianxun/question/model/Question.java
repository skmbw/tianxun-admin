package com.vteba.tianxun.question.model;

import java.util.Date;

public class Question {
    /**
     * order by 排序语句
     * 对应数据库表字段 question
     */
    private String orderBy;

    /**
     * 分页开始记录
     * 对应数据库表字段 question
     */
    private Integer start;

    /**
     * 分页大小
     * 对应数据库表字段 question
     */
    private int pageSize = 10;

    /**
     * 是否去重
     * 对应数据库表字段 question
     */
    private boolean distinct;

    /**
     * 对应数据库表字段 question.id
     */
    private String id;

    /**
     * 对应数据库表字段 question.title
     */
    private String title;

    /**
     * 对应数据库表字段 question.content
     */
    private String content;

    /**
     * 对应数据库表字段 question.summary
     */
    private String summary;

    /**
     * 对应数据库表字段 question.price
     */
    private Double price;

    /**
     * 对应数据库表字段 question.asker
     */
    private String asker;

    /**
     * 对应数据库表字段 question.asker_name
     */
    private String askerName;

    /**
     * 对应数据库表字段 question.ask_date
     */
    private Date askDate;

    /**
     * 对应数据库表字段 question.answer_date
     */
    private Date answerDate;

    /**
     * 对应数据库表字段 question.close_date
     */
    private Date closeDate;

    /**
     * 对应数据库表字段 question.solved
     */
    private Boolean solved;

    /**
     * 对应数据库表字段 question.open
     */
    private Boolean open;

    /**
     * 对应数据库表字段 question.platform
     */
    private Boolean platform;

    /**
     * 对应数据库表字段 question.category
     */
    private Integer category;

    /**
     * 对应数据库表字段 question.category_name
     */
    private String categoryName;

    /**
     * 对应数据库表字段 question.satisfied_answer_id
     */
    private String satisfiedAnswerId;

    /**
     * 对应数据库表字段 question.satisfied_user_id
     */
    private String satisfiedUserId;

    /**
     * 对应数据库表字段 question.satisfied_user_name
     */
    private String satisfiedUserName;

    /**
     * 对应数据库表字段 question.state
     */
    private Integer state;

    private String authCode;
    
    /**
     * 设置 order by 排序语句
     */
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    /**
     * 获得 order by 排序语句
     */
    public String getOrderBy() {
        return orderBy;
    }

    /**
     * 设置 start，分页开始记录
     */
    public void setStart(Integer start) {
        this.start = start;
    }

    /**
     * 获得 start，分页开始记录
     */
    public Integer getStart() {
        return start;
    }

    /**
     * 设置 pageSize，分页大小
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获得 pageSize，分页大小
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置 distinct，是否去重
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * 获得 distinct，是否去重
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * 获得字段 question.id 的值
     *
     * @return the value of question.id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置字段 question.id 的值
     *
     * @param id the value for question.id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获得字段 question.title 的值
     *
     * @return the value of question.title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置字段 question.title 的值
     *
     * @param title the value for question.title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获得字段 question.content 的值
     *
     * @return the value of question.content
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置字段 question.content 的值
     *
     * @param content the value for question.content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获得字段 question.summary 的值
     *
     * @return the value of question.summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 设置字段 question.summary 的值
     *
     * @param summary the value for question.summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * 获得字段 question.price 的值
     *
     * @return the value of question.price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 设置字段 question.price 的值
     *
     * @param price the value for question.price
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 获得字段 question.asker 的值
     *
     * @return the value of question.asker
     */
    public String getAsker() {
        return asker;
    }

    /**
     * 设置字段 question.asker 的值
     *
     * @param asker the value for question.asker
     */
    public void setAsker(String asker) {
        this.asker = asker;
    }

    /**
     * 获得字段 question.asker_name 的值
     *
     * @return the value of question.asker_name
     */
    public String getAskerName() {
        return askerName;
    }

    /**
     * 设置字段 question.asker_name 的值
     *
     * @param askerName the value for question.asker_name
     */
    public void setAskerName(String askerName) {
        this.askerName = askerName;
    }

    /**
     * 获得字段 question.ask_date 的值
     *
     * @return the value of question.ask_date
     */
    public Date getAskDate() {
        return askDate;
    }

    /**
     * 设置字段 question.ask_date 的值
     *
     * @param askDate the value for question.ask_date
     */
    public void setAskDate(Date askDate) {
        this.askDate = askDate;
    }

    /**
     * 获得字段 question.answer_date 的值
     *
     * @return the value of question.answer_date
     */
    public Date getAnswerDate() {
        return answerDate;
    }

    /**
     * 设置字段 question.answer_date 的值
     *
     * @param answerDate the value for question.answer_date
     */
    public void setAnswerDate(Date answerDate) {
        this.answerDate = answerDate;
    }

    /**
     * 获得字段 question.close_date 的值
     *
     * @return the value of question.close_date
     */
    public Date getCloseDate() {
        return closeDate;
    }

    /**
     * 设置字段 question.close_date 的值
     *
     * @param closeDate the value for question.close_date
     */
    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    /**
     * 获得字段 question.solved 的值
     *
     * @return the value of question.solved
     */
    public Boolean getSolved() {
        return solved;
    }

    /**
     * 设置字段 question.solved 的值
     *
     * @param solved the value for question.solved
     */
    public void setSolved(Boolean solved) {
        this.solved = solved;
    }

    /**
     * 获得字段 question.open 的值
     *
     * @return the value of question.open
     */
    public Boolean getOpen() {
        return open;
    }

    /**
     * 设置字段 question.open 的值
     *
     * @param open the value for question.open
     */
    public void setOpen(Boolean open) {
        this.open = open;
    }

    /**
     * 获得字段 question.platform 的值
     *
     * @return the value of question.platform
     */
    public Boolean getPlatform() {
        return platform;
    }

    /**
     * 设置字段 question.platform 的值
     *
     * @param platform the value for question.platform
     */
    public void setPlatform(Boolean platform) {
        this.platform = platform;
    }

    /**
     * 获得字段 question.category 的值
     *
     * @return the value of question.category
     */
    public Integer getCategory() {
        return category;
    }

    /**
     * 设置字段 question.category 的值
     *
     * @param category the value for question.category
     */
    public void setCategory(Integer category) {
        this.category = category;
    }

    /**
     * 获得字段 question.category_name 的值
     *
     * @return the value of question.category_name
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * 设置字段 question.category_name 的值
     *
     * @param categoryName the value for question.category_name
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * 获得字段 question.satisfied_answer_id 的值
     *
     * @return the value of question.satisfied_answer_id
     */
    public String getSatisfiedAnswerId() {
        return satisfiedAnswerId;
    }

    /**
     * 设置字段 question.satisfied_answer_id 的值
     *
     * @param satisfiedAnswerId the value for question.satisfied_answer_id
     */
    public void setSatisfiedAnswerId(String satisfiedAnswerId) {
        this.satisfiedAnswerId = satisfiedAnswerId;
    }

    /**
     * 获得字段 question.satisfied_user_id 的值
     *
     * @return the value of question.satisfied_user_id
     */
    public String getSatisfiedUserId() {
        return satisfiedUserId;
    }

    /**
     * 设置字段 question.satisfied_user_id 的值
     *
     * @param satisfiedUserId the value for question.satisfied_user_id
     */
    public void setSatisfiedUserId(String satisfiedUserId) {
        this.satisfiedUserId = satisfiedUserId;
    }

    /**
     * 获得字段 question.satisfied_user_name 的值
     *
     * @return the value of question.satisfied_user_name
     */
    public String getSatisfiedUserName() {
        return satisfiedUserName;
    }

    /**
     * 设置字段 question.satisfied_user_name 的值
     *
     * @param satisfiedUserName the value for question.satisfied_user_name
     */
    public void setSatisfiedUserName(String satisfiedUserName) {
        this.satisfiedUserName = satisfiedUserName;
    }

    /**
     * 获得字段 question.state 的值
     *
     * @return the value of question.state
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置字段 question.state 的值
     *
     * @param state the value for question.state
     */
    public void setState(Integer state) {
        this.state = state;
    }

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
}