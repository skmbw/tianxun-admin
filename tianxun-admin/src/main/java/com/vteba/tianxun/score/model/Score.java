package com.vteba.tianxun.score.model;

import java.util.Date;

public class Score {
    /**
     * order by 排序语句
     * 对应数据库表字段 score
     */
    private String orderBy;

    /**
     * 分页开始记录
     * 对应数据库表字段 score
     */
    private Integer start;

    /**
     * 分页大小
     * 对应数据库表字段 score
     */
    private int pageSize = 10;

    /**
     * 是否去重
     * 对应数据库表字段 score
     */
    private boolean distinct;

    /**
     * 对应数据库表字段 score.id
     */
    private String id;

    /**
     * 对应数据库表字段 score.user_id
     */
    private String userId;

    /**
     * 对应数据库表字段 score.year
     */
    private String year;

    /**
     * 对应数据库表字段 score.score
     */
    private Long score;

    /**
     * 对应数据库表字段 score.last_score
     */
    private Long lastScore;

    /**
     * 对应数据库表字段 score.state
     */
    private Integer state;

    /**
     * 对应数据库表字段 score.create_date
     */
    private Date createDate;

    /**
     * 对应数据库表字段 score.update_date
     */
    private Date updateDate;

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
     * 获得字段 score.id 的值
     *
     * @return the value of score.id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置字段 score.id 的值
     *
     * @param id the value for score.id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获得字段 score.user_id 的值
     *
     * @return the value of score.user_id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置字段 score.user_id 的值
     *
     * @param userId the value for score.user_id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获得字段 score.year 的值
     *
     * @return the value of score.year
     */
    public String getYear() {
        return year;
    }

    /**
     * 设置字段 score.year 的值
     *
     * @param year the value for score.year
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * 获得字段 score.score 的值
     *
     * @return the value of score.score
     */
    public Long getScore() {
        return score;
    }

    /**
     * 设置字段 score.score 的值
     *
     * @param score the value for score.score
     */
    public void setScore(Long score) {
        this.score = score;
    }

    /**
     * 获得字段 score.last_score 的值
     *
     * @return the value of score.last_score
     */
    public Long getLastScore() {
        return lastScore;
    }

    /**
     * 设置字段 score.last_score 的值
     *
     * @param lastScore the value for score.last_score
     */
    public void setLastScore(Long lastScore) {
        this.lastScore = lastScore;
    }

    /**
     * 获得字段 score.state 的值
     *
     * @return the value of score.state
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置字段 score.state 的值
     *
     * @param state the value for score.state
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获得字段 score.create_date 的值
     *
     * @return the value of score.create_date
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置字段 score.create_date 的值
     *
     * @param createDate the value for score.create_date
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获得字段 score.update_date 的值
     *
     * @return the value of score.update_date
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置字段 score.update_date 的值
     *
     * @param updateDate the value for score.update_date
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}