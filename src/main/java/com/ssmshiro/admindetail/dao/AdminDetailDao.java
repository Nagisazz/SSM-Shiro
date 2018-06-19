package com.ssmshiro.admindetail.dao;


import com.ssmshiro.admindetail.entity.AdminDetail;
import com.ssmshiro.base.dao.BaseDao;

public interface AdminDetailDao extends BaseDao<AdminDetail> {

    AdminDetail get(String username);
}