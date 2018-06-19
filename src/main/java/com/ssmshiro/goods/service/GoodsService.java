package com.ssmshiro.goods.service;

import com.ssmshiro.base.service.BaseService;
import com.ssmshiro.common.exception.InfoCode;
import com.ssmshiro.common.exception.ServiceException;
import com.ssmshiro.goods.dao.GoodsDao;
import com.ssmshiro.goods.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GoodsService extends BaseService<GoodsDao,Goods> {

    @Autowired
    private GoodsDao goodsDao;

    @Transactional(readOnly = false)
    public void save(Goods goods){
        try{
            goodsDao.insert(goods);
        }catch (Exception e){
            throw new ServiceException(InfoCode.EMSG0001);
        }
    }

    public Goods get(Long id){
        try{
            return goodsDao.selectByPrimaryKey(id);
        }catch (Exception e){
            throw new ServiceException(InfoCode.EMSG0004);
        }
    }
}
