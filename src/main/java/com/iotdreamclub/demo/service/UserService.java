package com.iotdreamclub.demo.service;

import com.iotdreamclub.demo.entity.User;
import com.iotdreamclub.demo.entity.UserLoginInfo;

import java.sql.Date;
import java.util.List;

public interface UserService {
    User login (String username , String password);
    void register (String username , String password);
    void changePersonalInfomation(String username, String password ,String userIdNumber , String userPhone , String userClassName , String userLimit);
    User selectUserByName(String username);
    User selectLimitByName(String userlimit);
    List<User> selectAll();

    void insertLoginInfo(String username , String loginIpAddress ,String loginAddress);
    List<UserLoginInfo> selectAllLoginInfo();
    void changeUserPassword(String password , String userIdNumber);

    void sendSMSCode(String userPhone);
}
