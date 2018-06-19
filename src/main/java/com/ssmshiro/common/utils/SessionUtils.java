package com.ssmshiro.common.utils;

import com.ssmshiro.admindetail.entity.AdminDetail;
import com.ssmshiro.common.constants.Constants;
import com.ssmshiro.common.shiro.token.LoginToken;
import com.ssmshiro.common.thirdutils.JedisUtils;
import com.ssmshiro.persondetail.entity.PersonDetail;
import com.ssmshiro.user.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 会话操作类
 */
public class SessionUtils {

    private static String algorithmName = "md5";
    private static final int hashIterations = 2;

    /**
     * 用户登陆
     *
     * @param user
     * @return
     */
    public static String login(User<?> user) throws Exception {

        String type = null;
        if (user instanceof PersonDetail) {
            type = Constants.USER_PERSON;
        }
        if (user instanceof AdminDetail) {
            type = Constants.USER_ADMIN;
        }

        //生成一个随机token
        String token = UUID.randomUUID().toString();

        String newPassword = new SimpleHash(algorithmName, user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()), hashIterations).toHex();
        user.setPassword(newPassword);

        try {
            LoginToken loginToken = new LoginToken(user.getUsername(), user.getPassword(), type, token);
            Subject subject = SecurityUtils.getSubject();

            subject.login(loginToken);

            //存储用户信息进redis，存储用户ID和用户类型，以后可以进行添加
            List<String> userList = new ArrayList<>();
            userList.add(user.getUsername().toString());

            //存储用户类型
            if (user instanceof PersonDetail) {
                userList.add(Constants.USER_PERSON);
            }
            if (user instanceof AdminDetail) {
                userList.add(Constants.USER_ADMIN);
            }

            //设置过期时间，即token失效时间
            JedisUtils.setList(token, userList, Constants.TOKEN_TIME);

            return token;
        } catch (Exception e) {
            throw new IncorrectCredentialsException(e);
        }
    }

    /**
     * 获取当前登陆用户
     *
     * @return
     */
    public static <T> T getLoginUser() {
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        List<String> list = JedisUtils.getList(user.getToken());
        String type = list.get(1);
        User result = null;
        if(type.equals(Constants.USER_ADMIN)){
            result = (AdminDetail) subject.getPrincipal();
        }
        else if(type.equals(Constants.USER_PERSON)){
            result = (PersonDetail) subject.getPrincipal();
        }
        else {
            result = (PersonDetail) subject.getPrincipal();
        }
        return (T) result;
    }
}
