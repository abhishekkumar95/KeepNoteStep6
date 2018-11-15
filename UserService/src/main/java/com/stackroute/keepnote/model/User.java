package com.stackroute.keepnote.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Document
public class User {

    @Id
    private String userId;
    private  String userName;
    private String userPassword;
    private String userMobile;
    private Date userAddedDate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public Date getUserAddedDate() {
        return userAddedDate;
    }

    public void setUserAddedDate(Date userAddedDate) {
        this.userAddedDate = userAddedDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userMobile='" + userMobile + '\'' +
                ", userAddedDate=" + userAddedDate +
                '}';
    }
}
