package com.ssmshiro.base.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssmshiro.common.thirdutils.Page;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 实体基类
 * @param <T>
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class BaseEntity<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 返回的分页对象
     */
    @XmlTransient
    private Page<T> resultPage;

    /**
     * 分页对象
     */
    @JSONField(serialize = false)
    @JsonIgnore
    @XmlTransient
    private Page<T> page;

    /**
     * 分页查询基类
     * 代理模式实现
     */
    @JSONField(serialize = false)
    @JsonIgnore
    @XmlTransient
    protected BasePageEntity basePageEntity;

    @JsonIgnore
    public Page<T> getPage() {
        if (page == null){
            page = new Page<T>();
        }
        return page;
    }

    public void setPage(Page<T> page) {
        this.page = page;
    }

    public BasePageEntity getBasePageEntity() {
        return basePageEntity;
    }

    public void setBasePageEntity(BasePageEntity basePageEntity) {
        this.basePageEntity = basePageEntity;
    }

    public Page<T> getResultPage() {
        return resultPage;
    }

    public void setResultPage(Page<T> resultPage) {
        this.resultPage = resultPage;
    }

    /**
     * 进行分页处理
     * @param basePageEntity
     */
    public void setPage(BasePageEntity basePageEntity) {
        this.basePageEntity = basePageEntity;

        if (basePageEntity != null) {
            this.setPage(new Page<T>(basePageEntity.getPageNo(),basePageEntity.getPageCount()));
        }
    }

    /**
     * 分页返回
     * @param baseEntity
     * @return
     */
    public Page<T> getPageResult(List<T> baseEntity){
        if(resultPage == null){
            resultPage = new Page<T>();
        }
        else {
            this.getPage().initialize();
            resultPage.setTotalRecord(this.getPage().getCount());
            resultPage.setPageNo(this.getPage().getPageNo());
            resultPage.setPageCount(this.getPage().getPageSize());
            if(basePageEntity.getPageNo() <= this.getPage().getLast()
                    && basePageEntity.getPageNo() >= this.getPage().getFirst()){
                resultPage.setObjectList(baseEntity);
            }else{
                resultPage.setObjectList(new ArrayList<T>());
            }
        }
        return resultPage;
    }
}
