package com.vteba.tianxun.question.model;

import java.util.Date;

public class QuestionImage {
    /**
     * order by 排序语句
     * 对应数据库表字段 question_image
     */
    private String orderBy;

    /**
     * 分页开始记录
     * 对应数据库表字段 question_image
     */
    private Integer start;

    /**
     * 分页大小
     * 对应数据库表字段 question_image
     */
    private int pageSize = 10;

    /**
     * 是否去重
     * 对应数据库表字段 question_image
     */
    private boolean distinct;

    /**
     * 对应数据库表字段 question_image.id
     */
    private String id;

    /**
     * 对应数据库表字段 question_image.question_id
     */
    private String questionId;

    /**
     * 对应数据库表字段 question_image.image_path
     */
    private String imagePath;

    /**
     * 对应数据库表字段 question_image.create_date
     */
    private Date createDate;

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
     * 获得字段 question_image.id 的值
     *
     * @return the value of question_image.id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置字段 question_image.id 的值
     *
     * @param id the value for question_image.id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获得字段 question_image.question_id 的值
     *
     * @return the value of question_image.question_id
     */
    public String getQuestionId() {
        return questionId;
    }

    /**
     * 设置字段 question_image.question_id 的值
     *
     * @param questionId the value for question_image.question_id
     */
    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    /**
     * 获得字段 question_image.image_path 的值
     *
     * @return the value of question_image.image_path
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * 设置字段 question_image.image_path 的值
     *
     * @param imagePath the value for question_image.image_path
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * 获得字段 question_image.create_date 的值
     *
     * @return the value of question_image.create_date
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置字段 question_image.create_date 的值
     *
     * @param createDate the value for question_image.create_date
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}