package com.ssmshiro.common.shiro.realm;

import com.ssmshiro.admindetail.service.AdminDetailService;
import com.ssmshiro.common.constants.Constants;
import com.ssmshiro.common.shiro.token.LoginToken;
import com.ssmshiro.common.thirdutils.JedisUtils;
import com.ssmshiro.persondetail.service.PersonDetailService;
import com.ssmshiro.user.entity.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * shiro登录处理器
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private AdminDetailService adminDetailService;

    @Autowired
    private PersonDetailService personDetailService;

    /**
     * 权限认证
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        return null;
    }

    /**
     * 身份认证
     * 也就是验证用户输入的账号和密码是否正确
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {

        //获取用户token
        String username = (String) token.getPrincipal();

        User user = null;

        //取出用户id
        if (JedisUtils.exists(username)) {
            List<String> userList = JedisUtils.getList(username);

            //登陆用户若为admin
            if (userList.get(1).equals(Constants.USER_ADMIN)) {
                user = adminDetailService.get(userList.get(0));
            }
            //登陆用户若为person
            else {
                user = personDetailService.get(userList.get(0));
            }

            user.setToken(username);
        } else {
            LoginToken loginToken = (LoginToken) token;
            if (loginToken.getType().equals(Constants.USER_ADMIN)) {
                user = adminDetailService.get(username);
            } else {
                user = personDetailService.get(username);
            }
            user.setToken(loginToken.getToken());
        }

        //异常返回
        if (user == null) {
            return null;
        }

        //进行登陆
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user, //用户即存进principal的值
                user.getPassword(), //密码即存进credentials的值
                ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt
                getName()  //realm name
        );

        return authenticationInfo;

    }

}