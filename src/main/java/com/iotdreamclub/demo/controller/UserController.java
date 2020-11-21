package com.iotdreamclub.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.iotdreamclub.demo.config.RedisConfig;
import com.iotdreamclub.demo.entity.JsonResult;
import com.iotdreamclub.demo.entity.Role;
import com.iotdreamclub.demo.entity.User;
import com.iotdreamclub.demo.entity.UserLoginInfo;
import com.iotdreamclub.demo.service.FunctionService;
import com.iotdreamclub.demo.service.RoleModuleService;
import com.iotdreamclub.demo.service.RoleService;
import com.iotdreamclub.demo.service.UserService;
import net.ipip.ipdb.CityInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//用户管理操作函数

@Controller
public class UserController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleModuleService roleModuleService;

    @Autowired
    private FunctionService functionService;

    @RequestMapping("CUI/{username}")
    public String cui(@PathVariable String username , Model model){
        User user = userService.selectUserByName(username);
        List<Role> roleList = roleService.selectAllRoleByTable();
        model.addAttribute("roleLists",roleList);
        model.addAttribute("users",user);
        return "edit_change_user_info";
    }

    @RequestMapping("showUserLoginInfo")
    public void showUserLoginInfo(Model model){
        List<UserLoginInfo> userLoginInfos = userService.selectAllLoginInfo();
        model.addAttribute("userLoginInfo",userLoginInfos);
    }

    @RequestMapping("change_User_Info")
    @ResponseBody
    public String changeUserInfo(String password,
                                 String userIdNumber, String userPhone,
                                 String userClassName, String userLimit,
                                 HttpServletResponse response, String username){
        userService.changePersonalInfomation(username , password , userIdNumber , userPhone , userClassName , userLimit);
        try {
            response.sendRedirect("member_management");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @RequestMapping("changePersonalInfo")
    @ResponseBody
    public String changePersonalInfo(String username, String password ,
                                     String userIdNumber , String userPhone ,
                                     String userClassName , String userLimit ,
                                     HttpServletResponse response){
        userService.changePersonalInfomation(username , password , userIdNumber , userPhone , userClassName , userLimit);
        try {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.flush();
            out.println("<script>");
            out.println("alert('个人信息修改成功');");
            out.println("history.back();");
            out.println("</script>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            response.sendRedirect("persional_info_change/{username}");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return " ";
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(Model model,
                        HttpSession session,
                        String username ,
                        String password ,
                        HttpServletRequest request,
                        HttpServletResponse response) throws IOException {
        UsernamePasswordToken token = new UsernamePasswordToken();
        token.setUsername(username);
        token.setPassword(password.toCharArray());
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()){
            try {
                subject.login(token);
                String limit = userService.selectLimitByName(username).getUserLimit();
                session.setAttribute("limit",limit);
                session.setAttribute("username",username);
                model.addAttribute("loginUser",userService.selectUserByName(username));
                try {
                    String remoteAddr = functionService.getRemoteAddr(request);
                    CityInfo cityInfo = functionService.getAddrInfoFromDB(remoteAddr);
                    userService.insertLoginInfo(username,remoteAddr,cityInfo.getCountryName()+"-"+cityInfo.getRegionName()+"-"+cityInfo.getCityName());
                }catch (Exception e){
                    e.printStackTrace();
                }
                return "redirect:/index";
            } catch ( UnknownAccountException uae ) {
                System.out.println("用户名不存在");
            } catch ( IncorrectCredentialsException ice ) {
                System.out.println("密码错误");
            } catch ( LockedAccountException lae ) {
                System.out.println("用户被锁定，不能登录");
            } catch ( AuthenticationException ae ) {
                System.out.println("严重的错误");
            }
            response.setContentType("text/html; charset=UTF-8"); //转码
            PrintWriter out = response.getWriter();
            out.flush();
            out.println("<script>");
            out.println("alert('密码错误');");
            out.println("history.back();");
            out.println("</script>");
            return "redirect:/login";
        }
        else {
            subject.login(token);
            String limit = userService.selectLimitByName(username).getUserLimit();
            session.setAttribute("limit",limit);
            session.setAttribute("username",username);
            model.addAttribute("loginUser",userService.selectUserByName(username));
            model.addAttribute("msg","已经登陆");
            return "redirect:/index";
        }
    }

    @RequestMapping(value = "register",method = RequestMethod.POST)
    public String register(Model model, String username , String password , String userPhone ,String verifyNumber, HttpServletResponse response) throws IOException {
        User userByName = userService.selectUserByName(username);
        if (userByName != null ){
            response.setContentType("text/html; charset=UTF-8"); //转码
            PrintWriter out = response.getWriter();
            out.flush();
            out.println("<script>");
            out.println("alert('用户名已存在');");
            out.println("history.back();");
            out.println("</script>");
            return "forward:/register";
        }
        User userByPhone = userService.selectUserByPhone(userPhone);
        if (userByPhone != null){
            response.setContentType("text/html; charset=UTF-8"); //转码
            PrintWriter out = response.getWriter();
            out.flush();
            out.println("<script>");
            out.println("alert('手机号已存在');");
            out.println("history.back();");
            out.println("</script>");
            return "forward:/register";
        }
        RedisTemplate redisTemplate = RedisConfig.getRedisTemplate();
        Object o = redisTemplate.opsForValue().get("smscode");
        if(o==null){
            response.setContentType("text/html; charset=UTF-8"); //转码
            PrintWriter out = response.getWriter();
            out.flush();
            out.println("<script>");
            out.println("alert('验证码已过期');");
            out.println("history.back();");
            out.println("</script>");
            return "forward:/register";  // 表示验证码过期
        }else {
            if(verifyNumber.equals(o)){
                userService.register(username,password,userPhone);
                return "redirect:/login";  // 表示验证码没有问题
            }else {
                response.setContentType("text/html; charset=UTF-8"); //转码
                PrintWriter out = response.getWriter();
                out.flush();
                out.println("<script>");
                out.println("alert('验证码错误');");
                out.println("history.back();");
                out.println("</script>");
                return "forward:/register";  // 表示验证码错误
            }
        }
    }

    @RequestMapping("userNameExisted")
    @ResponseBody
    public Object userNameExisted(@RequestParam String username){

        User user = userService.selectUserByName(username);
        if (user != null){
            return "0";
        }
        return "1";
    }

    @RequestMapping("sendVerifyNumber")
    @ResponseBody
    public void sendVerifyNumber(@RequestParam String userPhone){
        try {
            userService.sendSMSCode(userPhone);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("验证码发送失败！");
        }
    }

    @RequestMapping("/checkCode")
    @ResponseBody
    public String checkCode(String smsCode) {
        RedisTemplate redisTemplate = RedisConfig.getRedisTemplate();
        Object o = redisTemplate.opsForValue().get("smscode");
        if(o==null){
            return "验证码过期";  // 表示验证码过期
        }else {
            if(smsCode.equals(o)){
                return "ok";  // 表示验证码没有问题
            }else {
                return "验证码错误";  // 表示验证码错误
            }
        }
    }
}
