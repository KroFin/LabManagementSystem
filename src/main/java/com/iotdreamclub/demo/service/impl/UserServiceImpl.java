package com.iotdreamclub.demo.service.impl;

import com.iotdreamclub.demo.dao.UserDao;
import com.iotdreamclub.demo.entity.User;
import com.iotdreamclub.demo.entity.UserLoginInfo;
import com.iotdreamclub.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User login(String username, String password) {
        return userDao.login(username,password);
    }

    @Override
    public void register(String username, String password) {
        userDao.register(username,password);
    }

    @Override
    public void changePersonalInfomation(String username, String password ,String userIdNumber , String userPhone , String userClassName , String userLimit) {
        userDao.changePersonalInfomation(username , password , userIdNumber ,userPhone , userClassName ,userLimit);
    }

    @Override
    public User selectUserByName(String username) {
        return userDao.selectUserByName(username);
    }

    @Override
    public User selectLimitByName(String userlimit) {
        return userDao.selectLimitByName(userlimit);
    }

    @Override
    public List<User> selectAll() {
        return userDao.selectAll();
    }

    @Override
    public void insertLoginInfo(String username, String loginIpAddress,String loginAddress) {
        userDao.insertLoginInfo(username,loginIpAddress,loginAddress);
    }

    @Override
    public List<UserLoginInfo> selectAllLoginInfo() {
        return userDao.selectAllLoginInfo();
    }
}
