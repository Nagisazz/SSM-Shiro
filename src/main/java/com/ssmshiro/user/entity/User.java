package com.ssmshiro.user.entity;


import com.ssmshiro.base.entity.BaseEntity;

public class User<T> extends BaseEntity<T> {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String password;
    private String salt;//加密密码的盐
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCredentialsSalt() {
        return this.username + this.salt;
    }
}
