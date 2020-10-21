package com.iotdreamclub.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("tb_user_login_info")
public class UserLoginInfo {

  private String loginUserName;
  private java.sql.Date loginTime;
  private String loginIpAddress;


  public String getLoginUserName() {
    return loginUserName;
  }

  public void setLoginUserName(String loginUserName) {
    this.loginUserName = loginUserName;
  }


  public java.sql.Date getLoginTime() {
    return loginTime;
  }

  public void setLoginTime(java.sql.Date loginTime) {
    this.loginTime = loginTime;
  }


  public String getLoginIpAddress() {
    return loginIpAddress;
  }

  public void setLoginIpAddress(String loginIpAddress) {
    this.loginIpAddress = loginIpAddress;
  }

}
