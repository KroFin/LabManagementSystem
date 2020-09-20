package com.iotdreamclub.demo.controller;

import com.iotdreamclub.demo.entity.RoleModule;
import com.iotdreamclub.demo.entity.User;
import com.iotdreamclub.demo.service.RoleModuleService;
import com.iotdreamclub.demo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleModuleService roleModuleService;

    @RequestMapping("changePersonalInfo")
    @ResponseBody
    public String changePersonalInfo(String username, String password ,String userIdNumber , String userPhone , String userClassName , String userLimit , HttpServletResponse response){
        userService.changePersonalInfomation(username , password , userIdNumber , userPhone , userClassName , userLimit);
        try {
            response.sendRedirect("persional_info_change/{username}");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return " ";
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(Model model, HttpSession session, String username , String password){
        UsernamePasswordToken token = new UsernamePasswordToken();
        token.setUsername(username);
        token.setPassword(password.toCharArray());
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()){
            subject.login(token);
            String limit = userService.selectLimitByName(username).getUserLimit();
            session.setAttribute("limit",limit);
            session.setAttribute("username",username);
            model.addAttribute("loginUser",userService.selectUserByName(username));
            return "redirect:/index";
        }
        model.addAttribute("msg","登陆出错");
        return "forward:/login";
    }

    @RequestMapping(value = "register",method = RequestMethod.POST)
    public String register(Model model, String username , String password){
        User user = userService.selectUserByName(username);
        if (user != null){
            model.addAttribute("msg","用户名已存在");
            return "forward:/register.html";
        }
        userService.register(username,password);
        model.addAttribute("msg","登陆出错");
        return "redirect:/login.html";
    }

//    @RequestMapping("logout")
//    public ModelAndView logout(HttpSession session , ){
//
//    }
}
