package com.vteba.tianxun.system.model;

import java.util.Date;

public class Params {
    /**
     * order by 排序语句
     * 对应数据库表字段 params
     */
    private String orderBy;

    /**
     * 分页开始记录
     * 对应数据库表字段 params
     */
    private Integer start;

    /**
     * 分页大小
     * 对应数据库表字段 params
     */
    private int pageSize = 10;

    /**
     * 是否去重
     * 对应数据库表字段 params
     */
    private boolean distinct;

    /**
     * 对应数据库表字段 params.id
     */
    private String id;

    /**
     * 对应数据库表字段 params.name
     */
    private String name;

    /**
     * 对应数据库表字段 params.code
     */
    private String code;

    /**
     * 对应数据库表字段 params.value
     */
    private String value;

    /**
     * 对应数据库表字段 params.state
     */
    private Integer state;

    /**
     * 对应数据库表字段 params.remark
     */
    private String remark;

    /**
     * 对应数据库表字段 params.create_date
     */
    private Date createDate;

    /**
     * 对应数据库表字段 params.update_date
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
     * 获得字段 params.id 的值
     *
     * @return the value of params.id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置字段 params.id 的值
     *
     * @param id the value for params.id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获得字段 params.name 的值
     *
     * @return the value of params.name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置字段 params.name 的值
     *
     * @param name the value for params.name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获得字段 params.code 的值
     *
     * @return the value of params.code
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置字段 params.code 的值
     *
     * @param code the value for params.code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获得字段 params.value 的值
     *
     * @return the value of params.value
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置字段 params.value 的值
     *
     * @param value the value for params.value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 获得字段 params.state 的值
     *
     * @return the value of params.state
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置字段 params.state 的值
     *
     * @param state the value for params.state
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获得字段 params.remark 的值
     *
     * @return the value of params.remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置字段 params.remark 的值
     *
     * @param remark the value for params.remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获得字段 params.create_date 的值
     *
     * @return the value of params.create_date
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置字段 params.create_date 的值
     *
     * @param createDate the value for params.create_date
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获得字段 params.update_date 的值
     *
     * @return the value of params.update_date
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置字段 params.update_date 的值
     *
     * @param updateDate the value for params.update_date
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}