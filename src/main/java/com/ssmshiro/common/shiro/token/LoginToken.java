package com.ssmshiro.common.shiro.token;

import org.apache.shiro.authc.UsernamePasswordToken;

public class LoginToken extends UsernamePasswordToken {

    private String type;
    private String token;

    public LoginToken(String username, String password, String type, String token) {
        super(username, password);
        this.type = type;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
