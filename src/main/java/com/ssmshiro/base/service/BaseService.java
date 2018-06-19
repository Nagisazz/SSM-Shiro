package com.ssmshiro.base.service;

import com.ssmshiro.base.dao.BaseDao;
import com.ssmshiro.base.entity.BaseEntity;
import com.ssmshiro.common.exception.InfoCode;
import com.ssmshiro.common.exception.ServiceException;
import com.ssmshiro.common.thirdutils.Page;
import com.ssmshiro.common.utils.SessionUtils;
import com.ssmshiro.user.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service层基类
 * @param <D>
 * @param <T>
 */
@Transactional(readOnly = true)
public class BaseService<D extends BaseDao<T>,T extends BaseEntity<T>> {

    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private D dao;

    /**
     * 根据主键Id查询数据
     * @param id
     */
    public T get(Long id){
        try{
            User user = SessionUtils.getLoginUser();
            if (user == null || user.getId() == null) {
                throw new ServiceException(InfoCode.ESYS0003);
            }
            return dao.selectByPrimaryKey(id);
        }catch (ServiceException e){
            throw new ServiceException(InfoCode.ESYS0003);
        }catch (Exception e){
            throw new ServiceException(InfoCode.EMSG0004);
        }
    }

    /**
     * 根据条件分页查询数据
     * entity为封装了查询条件的查询实体
     * @param entity
     * @return
     */
    public Page<T> getCondition(T entity){
        try{
            User user = SessionUtils.getLoginUser();
            if (user == null || user.getId() == null) {
                throw new ServiceException(InfoCode.ESYS0003);
            }

            /**
             * 一般设定传入的参数为BasePageEntity的子类（即T是BasePageEntity的子类）
             * 那么加入下面这段代码
             * 即让eneity去代理实现BasePageEntity
             * T entity = new T();
             * entity.setBasePageEntity(basePageEntity);
             * entity.setId();为了查出entity
             */

            List<T> list = dao.selectCondition(entity);
            return entity.getPageResult(list);
        }catch (ServiceException e){
            throw new ServiceException(InfoCode.ESYS0003);
        }catch (Exception e){
            throw new ServiceException(InfoCode.EMSG0004);
        }
    }

    /**
     * 插入所有数据
     * @param entity
     */
    @Transactional(readOnly = false)
    public void saveAll(T entity){
        try{
            User user = SessionUtils.getLoginUser();
            if (user == null || user.getId() == null) {
                throw new ServiceException(InfoCode.ESYS0003);
            }
            dao.insert(entity);
        }catch (ServiceException e){
            throw new ServiceException(InfoCode.ESYS0003);
        }catch (Exception e){
            throw new ServiceException(InfoCode.EMSG0001);
        }
    }

    /**
     * 插入选中数据
     * @param entity
     */
    @Transactional(readOnly = false)
    public void saveSelective(T entity){
        try{
            User user = SessionUtils.getLoginUser();
            if (user == null || user.getId() == null) {
                throw new ServiceException(InfoCode.ESYS0003);
            }
            dao.insertSelective(entity);
        }catch (ServiceException e){
            throw new ServiceException(InfoCode.ESYS0003);
        }catch (Exception e){
            throw new ServiceException(InfoCode.EMSG0001);
        }
    }

    /**
     * 根据主键Id更新数据
     * @param entity
     */
    @Transactional(readOnly = false)
    public void updateById(T entity){
        try{
            User user = SessionUtils.getLoginUser();
            if (user == null || user.getId() == null) {
                throw new ServiceException(InfoCode.ESYS0003);
            }
            dao.updateByPrimaryKey(entity);
        }catch (ServiceException e){
            throw new ServiceException(InfoCode.ESYS0003);
        }catch (Exception e){
            throw new ServiceException(InfoCode.EMSG0003);
        }
    }

    /**
     * 根据主键Id删除数据
     * @param id
     */
    @Transactional(readOnly = false)
    public void delete(Long id){
        try{
            User user = SessionUtils.getLoginUser();
            if (user == null || user.getId() == null) {
                throw new ServiceException(InfoCode.ESYS0003);
            }
            dao.deleteByPrimaryKey(id);
        }catch (ServiceException e){
            throw new ServiceException(InfoCode.ESYS0003);
        }catch (Exception e){
            throw new ServiceException(InfoCode.EMSG0002);
        }
    }

}
