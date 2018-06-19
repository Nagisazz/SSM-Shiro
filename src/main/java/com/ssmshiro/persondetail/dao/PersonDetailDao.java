package com.ssmshiro.persondetail.dao;


import com.ssmshiro.base.dao.BaseDao;
import com.ssmshiro.persondetail.entity.PersonDetail;

import java.util.List;

public interface PersonDetailDao extends BaseDao<PersonDetail> {

    PersonDetail get(String username);
}