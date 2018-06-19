package com.ssmshiro.admindetail.service;

import com.ssmshiro.admindetail.dao.AdminDetailDao;
import com.ssmshiro.admindetail.entity.AdminDetail;
import com.ssmshiro.base.service.BaseService;
import com.ssmshiro.common.exception.InfoCode;
import com.ssmshiro.common.exception.ServiceException;
import com.ssmshiro.common.thirdutils.JedisUtils;
import com.ssmshiro.common.utils.SessionUtils;
import com.ssmshiro.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 管理员操作Service
 */
@Service
public class AdminDetailService extends BaseService<AdminDetailDao, AdminDetail> {

    @Autowired
    private UserService<AdminDetail> userService;

    @Autowired
    private AdminDetailDao adminDetailDao;

    /**
     * 管理员注册
     *
     * @param adminDetail
     * @return
     */
    public AdminDetail register(AdminDetail adminDetail) {
        if (get(adminDetail.getUsername()) != null) {
            throw new ServiceException(InfoCode.ESYS0009);
        } else {
            adminDetail = userService.register(adminDetail);
            adminDetailDao.insert(adminDetail);
            return adminDetail;
        }
    }

    /**
     * 根据管理员Id获取管理员
     *
     * @param id
     * @return
     */
    public AdminDetail get(String id) {
        return adminDetailDao.get(id);
    }

    /**
     * 管理员登录
     *
     * @param adminDetail
     * @return
     */
    public String login(AdminDetail adminDetail) {
        try {
            AdminDetail adminDetail1 = adminDetailDao.get(adminDetail.getUsername());
            adminDetail.setSalt(adminDetail1.getSalt());
            return SessionUtils.login(adminDetail);
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
     * 管理员退出登录
     *
     * @param token
     */
    public void logout(String token) {
        try {
            AdminDetail adminDetail = SessionUtils.getLoginUser();
            if (adminDetail == null || adminDetail.getId() == null) {
                throw new ServiceException(InfoCode.ESYS0003);
            }
            //删除token
            JedisUtils.del(token);

            Subject subject = SecurityUtils.getSubject();
            subject.logout();
        } catch (ServiceException se) {
            throw new ServiceException(InfoCode.ESYS0003);
        } catch (Exception e) {
            throw new ServiceException(InfoCode.ESYS0002);
        }

    }
}
