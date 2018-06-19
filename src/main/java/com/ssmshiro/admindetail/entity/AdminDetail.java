package com.ssmshiro.admindetail.entity;


import com.ssmshiro.user.entity.User;

public class AdminDetail extends User<AdminDetail> {

    private String adminName;

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
}