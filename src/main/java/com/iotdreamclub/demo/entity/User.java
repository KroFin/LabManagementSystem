package com.iotdreamclub.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("tb_user")
public class User {

  private String userName;
  private String userPassword;
  private String userIdNumber;
  private String userPhone;
  private String userClassName;
  private String userLimit;


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


  public String getUserIdNumber() {
    return userIdNumber;
  }

  public void setUserIdNumber(String userIdNumber) {
    this.userIdNumber = userIdNumber;
  }


  public String getUserPhone() {
    return userPhone;
  }

  public void setUserPhone(String userPhone) {
    this.userPhone = userPhone;
  }


  public String getUserClassName() {
    return userClassName;
  }

  public void setUserClassName(String userClassName) {
    this.userClassName = userClassName;
  }


  public String getUserLimit() {
    return userLimit;
  }

  public void setUserLimit(String userLimit) {
    this.userLimit = userLimit;
  }

}
