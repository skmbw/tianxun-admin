package com.vteba.tianxun.comment.model;

import java.util.Date;

public class Comment {
    /**
     * order by 排序语句
     * 对应数据库表字段 comment
     */
    private String orderBy;

    /**
     * 分页开始记录
     * 对应数据库表字段 comment
     */
    private Integer start;

    /**
     * 分页大小
     * 对应数据库表字段 comment
     */
    private int pageSize = 10;

    /**
     * 是否去重
     * 对应数据库表字段 comment
     */
    private boolean distinct;

    /**
     * 对应数据库表字段 comment.id
     */
    private String id;

    /**
     * 对应数据库表字段 comment.content
     */
    private String content;

    /**
     * 对应数据库表字段 comment.user_id
     */
    private String userId;

    /**
     * 对应数据库表字段 comment.nick_name
     */
    private String nickName;

    /**
     * 对应数据库表字段 comment.comment_date
     */
    private Date commentDate;

    /**
     * 对应数据库表字段 comment.question_id
     */
    private String questionId;

    /**
     * 对应数据库表字段 comment.answer_id
     */
    private String answerId;

    /**
     * 对应数据库表字段 comment.orders
     */
    private Integer orders;

    /**
     * 对应数据库表字段 comment.state
     */
    private Integer state;

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
     * 获得字段 comment.id 的值
     *
     * @return the value of comment.id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置字段 comment.id 的值
     *
     * @param id the value for comment.id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获得字段 comment.content 的值
     *
     * @return the value of comment.content
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置字段 comment.content 的值
     *
     * @param content the value for comment.content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获得字段 comment.user_id 的值
     *
     * @return the value of comment.user_id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置字段 comment.user_id 的值
     *
     * @param userId the value for comment.user_id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获得字段 comment.nick_name 的值
     *
     * @return the value of comment.nick_name
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置字段 comment.nick_name 的值
     *
     * @param nickName the value for comment.nick_name
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 获得字段 comment.comment_date 的值
     *
     * @return the value of comment.comment_date
     */
    public Date getCommentDate() {
        return commentDate;
    }

    /**
     * 设置字段 comment.comment_date 的值
     *
     * @param commentDate the value for comment.comment_date
     */
    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    /**
     * 获得字段 comment.question_id 的值
     *
     * @return the value of comment.question_id
     */
    public String getQuestionId() {
        return questionId;
    }

    /**
     * 设置字段 comment.question_id 的值
     *
     * @param questionId the value for comment.question_id
     */
    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    /**
     * 获得字段 comment.answer_id 的值
     *
     * @return the value of comment.answer_id
     */
    public String getAnswerId() {
        return answerId;
    }

    /**
     * 设置字段 comment.answer_id 的值
     *
     * @param answerId the value for comment.answer_id
     */
    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    /**
     * 获得字段 comment.orders 的值
     *
     * @return the value of comment.orders
     */
    public Integer getOrders() {
        return orders;
    }

    /**
     * 设置字段 comment.orders 的值
     *
     * @param orders the value for comment.orders
     */
    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    /**
     * 获得字段 comment.state 的值
     *
     * @return the value of comment.state
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置字段 comment.state 的值
     *
     * @param state the value for comment.state
     */
    public void setState(Integer state) {
        this.state = state;
    }
}