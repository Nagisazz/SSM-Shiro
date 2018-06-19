package com.ssmshiro.base.dao;

import java.util.List;

/**
 * Dao层基类
 * @param <T>
 */
public interface BaseDao<T> {

    /**
     * 根据条件分页查询数据
     * @param entity
     * @return
     */
    List<T> selectCondition(T entity);

    /**
     * 根据主键Id查询数据
     * @param id
     * @return
     */
    T selectByPrimaryKey(Long id);

    /**
     * 插入所有数据
     * @param entity
     * @return
     */
    int insert(T entity);

    /**
     * 插入选中数据
     * @param entity
     * @return
     */
    int insertSelective(T entity);

    /**
     * 根据主键Id更新指定数据
     * @param entity
     * @return
     */
    int updateByPrimaryKeySelective(T entity);

    /**
     * 根据主键Id更新数据
     * @param entity
     * @return
     */
    int updateByPrimaryKey(T entity);

    /**
     * 根据主键Id删除数据
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);
}
