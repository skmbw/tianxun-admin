package com.vteba.tianxun.question.model;

public class Category {
    /**
     * order by 排序语句
     * 对应数据库表字段 category
     */
    private String orderBy;

    /**
     * 分页开始记录
     * 对应数据库表字段 category
     */
    private Integer start;

    /**
     * 分页大小
     * 对应数据库表字段 category
     */
    private int pageSize = 10;

    /**
     * 是否去重
     * 对应数据库表字段 category
     */
    private boolean distinct;

    /**
     * 对应数据库表字段 category.code
     */
    private Integer code;

    /**
     * 对应数据库表字段 category.name
     */
    private String name;

    /**
     * 对应数据库表字段 category.state
     */
    private Integer state;

    /**
     * 对应数据库表字段 category.level
     */
    private Integer level;

    /**
     * 对应数据库表字段 category.parent
     */
    private Integer parent;

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
     * 获得字段 category.code 的值
     *
     * @return the value of category.code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * 设置字段 category.code 的值
     *
     * @param code the value for category.code
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * 获得字段 category.name 的值
     *
     * @return the value of category.name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置字段 category.name 的值
     *
     * @param name the value for category.name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获得字段 category.state 的值
     *
     * @return the value of category.state
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置字段 category.state 的值
     *
     * @param state the value for category.state
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获得字段 category.level 的值
     *
     * @return the value of category.level
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置字段 category.level 的值
     *
     * @param level the value for category.level
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获得字段 category.parent 的值
     *
     * @return the value of category.parent
     */
    public Integer getParent() {
        return parent;
    }

    /**
     * 设置字段 category.parent 的值
     *
     * @param parent the value for category.parent
     */
    public void setParent(Integer parent) {
        this.parent = parent;
    }
}