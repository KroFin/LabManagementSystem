package com.iotdreamclub.demo.service.impl;

import com.iotdreamclub.demo.config.RedisConfig;
import com.iotdreamclub.demo.dao.UserDao;
import com.iotdreamclub.demo.entity.User;
import com.iotdreamclub.demo.entity.UserLoginInfo;
import com.iotdreamclub.demo.service.UserService;
import com.iotdreamclub.demo.utils.SmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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

    @Override
    public void changeUserPassword(String password, String userIdNumber) {
        userDao.changeUserPassword(password, userIdNumber);
    }

    @Override
    public void sendSMSCode(String userPhone) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i <6 ; i++) {
            stringBuffer.append(new Random().nextInt(9) + 1);
        }
        String code=stringBuffer.toString();

        // 2、发送短信
        SmsUtil.sendSms(userPhone,code);

        // 3、发送成，就直接讲验证码存入Redis中
        RedisTemplate redisTemplate = RedisConfig.getRedisTemplate();
        // 设置短信验证码有效期为1分钟（60s）
        redisTemplate.opsForValue().set("smscode",code,1, TimeUnit.MINUTES);
    }
}
