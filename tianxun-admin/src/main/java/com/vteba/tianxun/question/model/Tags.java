package com.vteba.tianxun.question.model;

import java.util.Date;

public class Tags {
    /**
     * order by 排序语句
     * 对应数据库表字段 tags
     */
    private String orderBy;

    /**
     * 分页开始记录
     * 对应数据库表字段 tags
     */
    private Integer start;

    /**
     * 分页大小
     * 对应数据库表字段 tags
     */
    private int pageSize = 10;

    /**
     * 是否去重
     * 对应数据库表字段 tags
     */
    private boolean distinct;

    /**
     * 对应数据库表字段 tags.id
     */
    private String id;

    /**
     * 对应数据库表字段 tags.name
     */
    private String name;

    /**
     * 对应数据库表字段 tags.state
     */
    private Integer state;

    /**
     * 对应数据库表字段 tags.hot
     */
    private Integer hot;

    /**
     * 对应数据库表字段 tags.create_date
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
     * 获得字段 tags.id 的值
     *
     * @return the value of tags.id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置字段 tags.id 的值
     *
     * @param id the value for tags.id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获得字段 tags.name 的值
     *
     * @return the value of tags.name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置字段 tags.name 的值
     *
     * @param name the value for tags.name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获得字段 tags.state 的值
     *
     * @return the value of tags.state
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置字段 tags.state 的值
     *
     * @param state the value for tags.state
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获得字段 tags.hot 的值
     *
     * @return the value of tags.hot
     */
    public Integer getHot() {
        return hot;
    }

    /**
     * 设置字段 tags.hot 的值
     *
     * @param hot the value for tags.hot
     */
    public void setHot(Integer hot) {
        this.hot = hot;
    }

    /**
     * 获得字段 tags.create_date 的值
     *
     * @return the value of tags.create_date
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置字段 tags.create_date 的值
     *
     * @param createDate the value for tags.create_date
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}