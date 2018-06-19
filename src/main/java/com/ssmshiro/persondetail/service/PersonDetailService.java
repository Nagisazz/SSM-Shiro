package com.ssmshiro.persondetail.service;

import com.ssmshiro.base.service.BaseService;
import com.ssmshiro.common.exception.InfoCode;
import com.ssmshiro.common.exception.ServiceException;
import com.ssmshiro.common.thirdutils.JedisUtils;
import com.ssmshiro.common.utils.SessionUtils;
import com.ssmshiro.persondetail.dao.PersonDetailDao;
import com.ssmshiro.persondetail.entity.PersonDetail;
import com.ssmshiro.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户操作Service
 */
@Service
public class PersonDetailService extends BaseService<PersonDetailDao, PersonDetail> {

    @Autowired
    private UserService<PersonDetail> userService;

    @Autowired
    private PersonDetailDao personDetailDao;

    /**
     * 用户注册
     *
     * @param personDetail
     * @return
     */
    public PersonDetail register(PersonDetail personDetail) {
        if (get(personDetail.getUsername()) != null) {
            throw new ServiceException(InfoCode.ESYS0009);
        } else {
            personDetail = userService.register(personDetail);
            personDetailDao.insert(personDetail);
            return personDetail;
        }
    }

    /**
     * 根据Id获取用户
     *
     * @param username
     * @return
     */
    public PersonDetail get(String username) {
        return personDetailDao.get(username);
    }

    /**
     * 用户登录
     *
     * @param personDetail
     * @return
     */
    public String login(PersonDetail personDetail) {
        try {
            PersonDetail person1 = personDetailDao.get(personDetail.getUsername());
            personDetail.setSalt(person1.getSalt());
            return SessionUtils.login(personDetail);
        } catch (Exception e) {
            if (UnknownAccountException.class.isInstance(e) || NullPointerException.class.isInstance(e)) {
                throw new ServiceException(InfoCode.ESYS0005);
            } else if (IncorrectCredentialsException.class.isInstance(e)) {
                throw new ServiceException(InfoCode.ESYS0006);
            } else {
                throw new ServiceException(InfoCode.ESYS0004);
            }
        }
    }

    /**
     * 用户退出登录
     *
     * @param token
     */
    public void logout(String token) {
        try {
            //删除token
            JedisUtils.del(token);

            Subject subject = SecurityUtils.getSubject();
            subject.logout();
        } catch (Exception e) {
            throw new ServiceException(InfoCode.ESYS0002);
        }
    }
}
