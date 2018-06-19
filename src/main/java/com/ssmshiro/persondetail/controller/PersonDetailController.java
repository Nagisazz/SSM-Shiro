package com.ssmshiro.persondetail.controller;

import com.ssmshiro.base.controller.BaseController;
import com.ssmshiro.common.exception.InfoCode;
import com.ssmshiro.common.response.ResultEntity;
import com.ssmshiro.persondetail.dao.PersonDetailDao;
import com.ssmshiro.persondetail.entity.PersonDetail;
import com.ssmshiro.persondetail.service.PersonDetailService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonDetailController extends BaseController<PersonDetailService, PersonDetailDao, PersonDetail> {
    @Autowired
    private PersonDetailService personDetailService;

    /**
     * 用户注册
     * @param personDetail
     * @return
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ResultEntity<PersonDetail> register(@RequestBody PersonDetail personDetail){
        personDetail = personDetailService.register(personDetail);
        return new ResultEntity<PersonDetail>().OK(personDetail, InfoCode.ISYS0007);
    }

    /**
     * 用户登录
     * @param personDetail
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResultEntity<PersonDetail> login(@RequestBody PersonDetail personDetail){
        String token = personDetailService.login(personDetail);
        personDetail.setToken(token);
        return new ResultEntity<PersonDetail>().OK(personDetail, InfoCode.ISYS0004);
    }

    @RequestMapping(value = "/testShiro",method = RequestMethod.GET)
    public ResultEntity<PersonDetail> testShiro(){
        PersonDetail personDetail = (PersonDetail) SecurityUtils.getSubject().getPrincipal();
        return new ResultEntity<PersonDetail>().OK(personDetail, InfoCode.IMSG0004);

    }

    /**
     * 用户退出登录
     * @return
     */
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public ResultEntity<String> logout(@RequestParam("token")String token){
        personDetailService.logout(token);
        return new ResultEntity<String>().OK(InfoCode.ISYS0008);
    }
}
