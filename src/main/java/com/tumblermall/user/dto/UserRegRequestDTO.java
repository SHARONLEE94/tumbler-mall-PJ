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

    private int userId;
    String recipientName;
    String userAddress;
    String userAddressDetail;
    String userPostalCode;
    String isDefault;

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


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserAddressDetail() {
        return userAddressDetail;
    }

    public void setUserAddressDetail(String userAddressDetail) {
        this.userAddressDetail = userAddressDetail;
    }

    public String getUserPostalCode() {
        return userPostalCode;
    }

    public void setUserPostalCode(String userPostalCode) {
        this.userPostalCode = userPostalCode;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }
}
