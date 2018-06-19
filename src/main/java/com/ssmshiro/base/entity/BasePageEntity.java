package com.ssmshiro.base.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 分页实体基类
 * @param <T>
 */
public abstract class BasePageEntity<T> implements Serializable{

    private static final long serialVersionUID = 1L;

    private static final int DEFAULT_PAGE_COUNT = 10;
    private static final int DEFAULT_PAGE_NO = 1;

    /**
     * 当前页
     */
    private Integer pageNo;

    /**
     * 每页数据量
     */
    private Integer pageCount;

    /**
     * 起始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 查询关键字
     */
    private String search;

    public Integer getPageNo() {
        if (pageNo == null || pageNo == 0) {
            return DEFAULT_PAGE_NO;
        }
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageCount() {
        if (pageCount == null || pageCount == 0) {
            return DEFAULT_PAGE_COUNT;
        }
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
