package com.ssmshiro.base.controller;


import com.ssmshiro.base.dao.BaseDao;
import com.ssmshiro.base.entity.BaseEntity;
import com.ssmshiro.base.service.BaseService;
import com.ssmshiro.common.exception.InfoCode;
import com.ssmshiro.common.response.ResultEntity;
import com.ssmshiro.common.thirdutils.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Controller层基类
 */
public class BaseController<S extends BaseService<D, T>, D extends BaseDao<T>, T extends BaseEntity<T>> {

    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private S service;

    /**
     * 根据主键Id查询数据
     * @param id
     * @return
     */
    public @ResponseBody
    ResultEntity<T> get(@PathVariable Long id){
        return new ResultEntity<T>().OK(service.get(id), InfoCode.IMSG0004);
    }

    /**
     * 根据条件分页查询数据
     * entity为封装了查询条件的查询实体
     *
     * 一般设定传入的参数为BasePageEntity的子类（即T是BasePageEntity的子类）
     * 那么见Service层的相应改动
     * {@link BaseService}
     * @param entity
     * @return
     */
    public @ResponseBody
    ResultEntity<Page<T>> getCondition(@RequestBody T entity){
        return new ResultEntity<Page<T>>().OK(service.getCondition(entity), InfoCode.IMSG0004);
    }

    /**
     * 插入所有数据
     * @param entity
     * @return
     */
    public @ResponseBody
    ResultEntity<T> saveAll(@RequestBody T entity){
        service.saveAll(entity);
        return new ResultEntity<T>().OK(entity, InfoCode.IMSG0001);
    }

    /**
     * 插入选中数据
     * @param entity
     * @return
     */
    public @ResponseBody
    ResultEntity<T> saveSelective(@RequestBody T entity){
        service.saveSelective(entity);
        return new ResultEntity<T>().OK(entity, InfoCode.IMSG0001);
    }

    /**
     * 根据主键Id更新数据
     * @param entity
     * @return
     */
    public @ResponseBody
    ResultEntity<T> updateById(@RequestBody T entity){
        service.updateById(entity);
        return new ResultEntity<T>().OK(entity, InfoCode.IMSG0003);
    }

    /**
     * 根据主键Id删除数据
     * @param id
     * @return
     */
    public @ResponseBody
    ResultEntity<Long> delete(@PathVariable Long id){
        service.delete(id);
        return new ResultEntity<Long>().OK(id, InfoCode.IMSG0002);
    }

}
