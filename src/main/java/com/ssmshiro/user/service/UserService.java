package com.ssmshiro.user.service;

import com.ssmshiro.user.entity.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;

/**
 * 用户抽象基础Service
 * @param <T>
 */
@Service
public class UserService<T extends User> {

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    private String algorithmName = "md5";
    private final int hashIterations = 2;

    public T register(T user) {
        try {
            //生成一个随机的盐
            user.setSalt(randomNumberGenerator.nextBytes().toHex());

            //将用户的注册密码经过散列算法替换成一个不可逆的新密码保存进数据，散列过程使用了盐
            String newPassword = new SimpleHash(algorithmName, user.getPassword(),
                    ByteSource.Util.bytes(user.getCredentialsSalt()), hashIterations).toHex();
            user.setPassword(newPassword);

            return user;
        } catch (Exception e) {
            return user;
        }
    }

}
