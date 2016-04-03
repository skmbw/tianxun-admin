package com.vteba.tianxun.account.model;

import java.util.Date;

public class PlatformDetail {
    /**
     * order by 排序语句
     * 对应数据库表字段 platform_detail
     */
    private String orderBy;

    /**
     * 分页开始记录
     * 对应数据库表字段 platform_detail
     */
    private Integer start;

    /**
     * 分页大小
     * 对应数据库表字段 platform_detail
     */
    private int pageSize = 10;

    /**
     * 是否去重
     * 对应数据库表字段 platform_detail
     */
    private boolean distinct;

    /**
     * 对应数据库表字段 platform_detail.id
     */
    private String id;

    /**
     * 对应数据库表字段 platform_detail.user_id
     */
    private String userId;

    /**
     * 对应数据库表字段 platform_detail.account_id
     */
    private String accountId;

    /**
     * 对应数据库表字段 platform_detail.amount
     */
    private Double amount;

    /**
     * 对应数据库表字段 platform_detail.category
     */
    private Integer category;

    /**
     * 对应数据库表字段 platform_detail.operation_time
     */
    private Date operationTime;

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
     * 获得字段 platform_detail.id 的值
     *
     * @return the value of platform_detail.id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置字段 platform_detail.id 的值
     *
     * @param id the value for platform_detail.id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获得字段 platform_detail.user_id 的值
     *
     * @return the value of platform_detail.user_id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置字段 platform_detail.user_id 的值
     *
     * @param userId the value for platform_detail.user_id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获得字段 platform_detail.account_id 的值
     *
     * @return the value of platform_detail.account_id
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * 设置字段 platform_detail.account_id 的值
     *
     * @param accountId the value for platform_detail.account_id
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    /**
     * 获得字段 platform_detail.amount 的值
     *
     * @return the value of platform_detail.amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * 设置字段 platform_detail.amount 的值
     *
     * @param amount the value for platform_detail.amount
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * 获得字段 platform_detail.category 的值
     *
     * @return the value of platform_detail.category
     */
    public Integer getCategory() {
        return category;
    }

    /**
     * 设置字段 platform_detail.category 的值
     *
     * @param category the value for platform_detail.category
     */
    public void setCategory(Integer category) {
        this.category = category;
    }

    /**
     * 获得字段 platform_detail.operation_time 的值
     *
     * @return the value of platform_detail.operation_time
     */
    public Date getOperationTime() {
        return operationTime;
    }

    /**
     * 设置字段 platform_detail.operation_time 的值
     *
     * @param operationTime the value for platform_detail.operation_time
     */
    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }
}