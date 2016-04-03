package com.vteba.tianxun.account.model;

import java.util.Date;

public class Account {
    /**
     * order by 排序语句
     * 对应数据库表字段 account
     */
    private String orderBy;

    /**
     * 分页开始记录
     * 对应数据库表字段 account
     */
    private Integer start;

    /**
     * 分页大小
     * 对应数据库表字段 account
     */
    private int pageSize = 10;

    /**
     * 是否去重
     * 对应数据库表字段 account
     */
    private boolean distinct;

    /**
     * 对应数据库表字段 account.id
     */
    private String id;

    /**
     * 对应数据库表字段 account.alipay
     */
    private String alipay;

    /**
     * 对应数据库表字段 account.weixin
     */
    private String weixin;

    /**
     * 对应数据库表字段 account.balance
     */
    private Double balance;

    /**
     * 对应数据库表字段 account.user_id
     */
    private String userId;

    /**
     * 对应数据库表字段 account.account
     */
    private String account;

    /**
     * 对应数据库表字段 account.payment
     */
    private Double payment;

    /**
     * 对应数据库表字段 account.income
     */
    private Double income;

    /**
     * 对应数据库表字段 account.recharge
     */
    private Double recharge;

    /**
     * 对应数据库表字段 account.state
     */
    private Integer state;

    /**
     * 对应数据库表字段 account.create_date
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
     * 获得字段 account.id 的值
     *
     * @return the value of account.id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置字段 account.id 的值
     *
     * @param id the value for account.id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获得字段 account.alipay 的值
     *
     * @return the value of account.alipay
     */
    public String getAlipay() {
        return alipay;
    }

    /**
     * 设置字段 account.alipay 的值
     *
     * @param alipay the value for account.alipay
     */
    public void setAlipay(String alipay) {
        this.alipay = alipay;
    }

    /**
     * 获得字段 account.weixin 的值
     *
     * @return the value of account.weixin
     */
    public String getWeixin() {
        return weixin;
    }

    /**
     * 设置字段 account.weixin 的值
     *
     * @param weixin the value for account.weixin
     */
    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    /**
     * 获得字段 account.balance 的值
     *
     * @return the value of account.balance
     */
    public Double getBalance() {
        return balance;
    }

    /**
     * 设置字段 account.balance 的值
     *
     * @param balance the value for account.balance
     */
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    /**
     * 获得字段 account.user_id 的值
     *
     * @return the value of account.user_id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置字段 account.user_id 的值
     *
     * @param userId the value for account.user_id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获得字段 account.account 的值
     *
     * @return the value of account.account
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置字段 account.account 的值
     *
     * @param account the value for account.account
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获得字段 account.payment 的值
     *
     * @return the value of account.payment
     */
    public Double getPayment() {
        return payment;
    }

    /**
     * 设置字段 account.payment 的值
     *
     * @param payment the value for account.payment
     */
    public void setPayment(Double payment) {
        this.payment = payment;
    }

    /**
     * 获得字段 account.income 的值
     *
     * @return the value of account.income
     */
    public Double getIncome() {
        return income;
    }

    /**
     * 设置字段 account.income 的值
     *
     * @param income the value for account.income
     */
    public void setIncome(Double income) {
        this.income = income;
    }

    /**
     * 获得字段 account.recharge 的值
     *
     * @return the value of account.recharge
     */
    public Double getRecharge() {
        return recharge;
    }

    /**
     * 设置字段 account.recharge 的值
     *
     * @param recharge the value for account.recharge
     */
    public void setRecharge(Double recharge) {
        this.recharge = recharge;
    }

    /**
     * 获得字段 account.state 的值
     *
     * @return the value of account.state
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置字段 account.state 的值
     *
     * @param state the value for account.state
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获得字段 account.create_date 的值
     *
     * @return the value of account.create_date
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置字段 account.create_date 的值
     *
     * @param createDate the value for account.create_date
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}