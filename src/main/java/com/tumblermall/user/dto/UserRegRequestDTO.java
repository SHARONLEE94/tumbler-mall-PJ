package com.tumblermall.user.dto;

import java.sql.Date;

public class UserRegRequestDTO {
    String userName;
    String userPwd;
    String userPhone;
    String userEmail;
    String userGenderId;
    String userJobId;
    Date userBirthDate;
    String userIsAdReceive;
    int emailVerifyCode;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserGenderId() {
        return userGenderId;
    }

    public void setUserGenderId(String userGenderId) {
        this.userGenderId = userGenderId;
    }

    public String getUserJobId() {
        return userJobId;
    }

    public void setUserJobId(String userJobId) {
        this.userJobId = userJobId;
    }

    public String getUserIsAdReceive() {
        return userIsAdReceive;
    }

    public void setUserIsAdReceive(String userIsAdReceive) {
        this.userIsAdReceive = userIsAdReceive;
    }

    public Date getUserBirthDate() {
        return userBirthDate;
    }

    public void setUserBirthDate(Date userBirthDate) {
        this.userBirthDate = userBirthDate;
    }

    public int getEmailVerifyCode() {
        return emailVerifyCode;
    }
    public void setEmailVerifyCode(int emailVerifyCode) {
        this.emailVerifyCode = emailVerifyCode;
    }
}
