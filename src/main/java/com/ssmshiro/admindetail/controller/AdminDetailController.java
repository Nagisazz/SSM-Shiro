package com.ssmshiro.admindetail.controller;

import com.ssmshiro.admindetail.dao.AdminDetailDao;
import com.ssmshiro.admindetail.entity.AdminDetail;
import com.ssmshiro.admindetail.service.AdminDetailService;
import com.ssmshiro.base.controller.BaseController;
import com.ssmshiro.common.exception.InfoCode;
import com.ssmshiro.common.response.ResultEntity;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminDetailController extends BaseController<AdminDetailService, AdminDetailDao, AdminDetail> {

    @Autowired
    private AdminDetailService adminDetailService;

    /**
     * 管理员注册
     * @param adminDetail
     * @return
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ResultEntity<AdminDetail> register(@RequestBody AdminDetail adminDetail){
        adminDetail = adminDetailService.register(adminDetail);
        return new ResultEntity<AdminDetail>().OK(adminDetail, InfoCode.ISYS0007);
    }

    /**
     * 管理员登录
     * @param adminDetail
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResultEntity<AdminDetail> login(@RequestBody AdminDetail adminDetail){
        String token = adminDetailService.login(adminDetail);
        adminDetail.setToken(token);
        return new ResultEntity<AdminDetail>().OK(adminDetail, InfoCode.ISYS0004);
    }

    @RequestMapping(value = "/testShiro",method = RequestMethod.GET)
    public ResultEntity<AdminDetail> testShiro(){
        AdminDetail adminDetail = (AdminDetail) SecurityUtils.getSubject().getPrincipal();
        return new ResultEntity<AdminDetail>().OK(adminDetail, InfoCode.IMSG0004);
    }

    /**
     * 管理员退出登录
     * @return
     */
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public ResultEntity<String> logout(@RequestParam("token")String token){
        adminDetailService.logout(token);
        return new ResultEntity<String>().OK(InfoCode.ISYS0008);
    }
}
