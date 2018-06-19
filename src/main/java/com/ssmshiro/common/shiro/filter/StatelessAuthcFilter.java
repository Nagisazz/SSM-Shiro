package com.ssmshiro.common.shiro.filter;

import com.ssmshiro.admindetail.service.AdminDetailService;
import com.ssmshiro.common.constants.Constants;
import com.ssmshiro.common.thirdutils.JedisUtils;
import com.ssmshiro.persondetail.service.PersonDetailService;
import com.ssmshiro.user.entity.User;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 自定义拦截器，拦截所有需要Token的请求
 */
@RestController
public class StatelessAuthcFilter extends AccessControlFilter {

    @Autowired
    private AdminDetailService adminDetailService;

    @Autowired
    private PersonDetailService personDetailService;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        try {
            //解决service为null无法注入问题
            if (adminDetailService == null) {
                BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
                adminDetailService = (AdminDetailService) factory.getBean("adminDetailService");
            }

            //解决service为null无法注入问题
            if (personDetailService == null) {
                BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
                personDetailService = (PersonDetailService) factory.getBean("personDetailService");
            }

            //委托给Realm进行登录
            String token = request.getParameter(Constants.ID_TOKEN);

            //判断token是否存在
//            if (JedisUtils.exists(token)) {
            //根据token取出用户信息
            List<String> userList = JedisUtils.getList(token);

            User user = null;

            //登陆用户若为admin
            if (userList.get(1).equals(Constants.USER_ADMIN)) {
                user = adminDetailService.get(userList.get(0));
            }
            //登陆用户若为person
            else {
                user = personDetailService.get(userList.get(0));
            }

            //更新token过期时间
            JedisUtils.setList(token, userList, Constants.TOKEN_TIME);

            //进行登陆
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(token, user.getPassword());
            getSubject(request, response).login(usernamePasswordToken);
//            }
        } catch (Exception e) {
            e.printStackTrace();
            onLoginFail(request,response); //登录失败
            return false;
        }
        return true;
    }

    // 登录失败时默认返回306状态码
    protected void onLoginFail(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(306);
        request.setAttribute("msg", "auth check error!");
        // 跳转到登录页
        redirectToLogin(request, httpResponse);
    }

//    @Override
//    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
//        HttpServletRequest hReq = (HttpServletRequest) request;
//        String rURL = hReq.getRequestURI();
//        String errors = StringUtils.isEmpty((String) request.getAttribute("msg")) ? "" : "&msg=" + request.getAttribute("msg");
//
//        if(request.getAttribute("msg") != null) {
//            rURL += ((StringUtils.isNotEmpty(hReq.getQueryString())) ?
//                    "&" : "") + "msg=" + request.getAttribute("msg");
//        }
//
//        rURL = Base64.encodeBase64URLSafeString(rURL.getBytes()) ;
//        // 加入登录前地址, 以及错误信息
//        String loginUrl = getLoginUrl() + "?r=" + rURL + errors;
//
//        WebUtils.issueRedirect(request, response, loginUrl);
//    }
}
