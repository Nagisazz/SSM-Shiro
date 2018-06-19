package com.ssmshiro.persondetail.entity;


import com.ssmshiro.user.entity.User;

import java.util.Date;

public class PersonDetail extends User<PersonDetail> {

    private String personName;

    private String personSex;

    private String personNation;

    private String personMarry;

    private Integer personAge;

    private Date personBirthday;

    private String personPhone;

    private String personOccutation;

    private String personAddress;

    private String personPicPath;

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName == null ? null : personName.trim();
    }

    public String getPersonSex() {
        return personSex;
    }

    public void setPersonSex(String personSex) {
        this.personSex = personSex == null ? null : personSex.trim();
    }

    public String getPersonNation() {
        return personNation;
    }

    public void setPersonNation(String personNation) {
        this.personNation = personNation == null ? null : personNation.trim();
    }

    public String getPersonMarry() {
        return personMarry;
    }

    public void setPersonMarry(String personMarry) {
        this.personMarry = personMarry == null ? null : personMarry.trim();
    }

    public Integer getPersonAge() {
        return personAge;
    }

    public void setPersonAge(Integer personAge) {
        this.personAge = personAge;
    }

    public Date getPersonBirthday() {
        return personBirthday;
    }

    public void setPersonBirthday(Date personBirthday) {
        this.personBirthday = personBirthday;
    }

    public String getPersonPhone() {
        return personPhone;
    }

    public void setPersonPhone(String personPhone) {
        this.personPhone = personPhone == null ? null : personPhone.trim();
    }

    public String getPersonOccutation() {
        return personOccutation;
    }

    public void setPersonOccutation(String personOccutation) {
        this.personOccutation = personOccutation == null ? null : personOccutation.trim();
    }

    public String getPersonAddress() {
        return personAddress;
    }

    public void setPersonAddress(String personAddress) {
        this.personAddress = personAddress == null ? null : personAddress.trim();
    }

    public String getPersonPicPath() {
        return personPicPath;
    }

    public void setPersonPicPath(String personPicPath) {
        this.personPicPath = personPicPath == null ? null : personPicPath.trim();
    }
}