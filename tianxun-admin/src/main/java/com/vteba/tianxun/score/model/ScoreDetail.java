package com.vteba.tianxun.score.model;

import java.util.Date;

public class ScoreDetail {
    /**
     * order by 排序语句
     * 对应数据库表字段 score_detail
     */
    private String orderBy;

    /**
     * 分页开始记录
     * 对应数据库表字段 score_detail
     */
    private Integer start;

    /**
     * 分页大小
     * 对应数据库表字段 score_detail
     */
    private int pageSize = 10;

    /**
     * 是否去重
     * 对应数据库表字段 score_detail
     */
    private boolean distinct;

    /**
     * 对应数据库表字段 score_detail.id
     */
    private String id;

    /**
     * 对应数据库表字段 score_detail.score_id
     */
    private String scoreId;

    /**
     * 对应数据库表字段 score_detail.score
     */
    private Integer score;

    /**
     * 对应数据库表字段 score_detail.key
     */
    private String key;

    /**
     * 对应数据库表字段 score_detail.key_type
     */
    private String keyType;

    /**
     * 对应数据库表字段 score_detail.create_date
     */
    private Date createDate;

    /**
     * 对应数据库表字段 score_detail.comment
     */
    private String comment;

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
     * 获得字段 score_detail.id 的值
     *
     * @return the value of score_detail.id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置字段 score_detail.id 的值
     *
     * @param id the value for score_detail.id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获得字段 score_detail.score_id 的值
     *
     * @return the value of score_detail.score_id
     */
    public String getScoreId() {
        return scoreId;
    }

    /**
     * 设置字段 score_detail.score_id 的值
     *
     * @param scoreId the value for score_detail.score_id
     */
    public void setScoreId(String scoreId) {
        this.scoreId = scoreId;
    }

    /**
     * 获得字段 score_detail.score 的值
     *
     * @return the value of score_detail.score
     */
    public Integer getScore() {
        return score;
    }

    /**
     * 设置字段 score_detail.score 的值
     *
     * @param score the value for score_detail.score
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * 获得字段 score_detail.key 的值
     *
     * @return the value of score_detail.key
     */
    public String getKey() {
        return key;
    }

    /**
     * 设置字段 score_detail.key 的值
     *
     * @param key the value for score_detail.key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 获得字段 score_detail.key_type 的值
     *
     * @return the value of score_detail.key_type
     */
    public String getKeyType() {
        return keyType;
    }

    /**
     * 设置字段 score_detail.key_type 的值
     *
     * @param keyType the value for score_detail.key_type
     */
    public void setKeyType(String keyType) {
        this.keyType = keyType;
    }

    /**
     * 获得字段 score_detail.create_date 的值
     *
     * @return the value of score_detail.create_date
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置字段 score_detail.create_date 的值
     *
     * @param createDate the value for score_detail.create_date
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获得字段 score_detail.comment 的值
     *
     * @return the value of score_detail.comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * 设置字段 score_detail.comment 的值
     *
     * @param comment the value for score_detail.comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
}