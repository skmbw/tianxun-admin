package com.vteba.tianxun.account.model;


public class PlatformAccount {
    /**
     * order by 排序语句
     * 对应数据库表字段 platform_account
     */
    private String orderBy;

    /**
     * 分页开始记录
     * 对应数据库表字段 platform_account
     */
    private Integer start;

    /**
     * 分页大小
     * 对应数据库表字段 platform_account
     */
    private int pageSize = 10;

    /**
     * 是否去重
     * 对应数据库表字段 platform_account
     */
    private boolean distinct;

    /**
     * 对应数据库表字段 platform_account.id
     */
    private String id;

    /**
     * 对应数据库表字段 platform_account.balance
     */
    private Double balance;

    /**
     * 对应数据库表字段 platform_account.total
     */
    private Double total;

    /**
     * 对应数据库表字段 platform_account.discount
     */
    private Double discount;

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
     * 获得字段 platform_account.id 的值
     *
     * @return the value of platform_account.id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置字段 platform_account.id 的值
     *
     * @param id the value for platform_account.id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获得字段 platform_account.balance 的值
     *
     * @return the value of platform_account.balance
     */
    public Double getBalance() {
        return balance;
    }

    /**
     * 设置字段 platform_account.balance 的值
     *
     * @param balance the value for platform_account.balance
     */
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    /**
     * 获得字段 platform_account.total 的值
     *
     * @return the value of platform_account.total
     */
    public Double getTotal() {
        return total;
    }

    /**
     * 设置字段 platform_account.total 的值
     *
     * @param total the value for platform_account.total
     */
    public void setTotal(Double total) {
        this.total = total;
    }

    /**
     * 获得字段 platform_account.discount 的值
     *
     * @return the value of platform_account.discount
     */
    public Double getDiscount() {
        return discount;
    }

    /**
     * 设置字段 platform_account.discount 的值
     *
     * @param discount the value for platform_account.discount
     */
    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}