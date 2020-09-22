package com.iotdreamclub.demo.controller;

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

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

//用户管理操作函数

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
    public String register(Model model, String username , String password ,String registerPassword,HttpServletResponse response) throws IOException {
        User user = userService.selectUserByName(username);
        if (user != null ){
            //model.addAttribute("msg","用户名已存在");
            response.setContentType("text/html; charset=UTF-8"); //转码
            PrintWriter out = response.getWriter();
            out.flush();
            out.println("<script>");
            out.println("alert('用户名已存在');");
            out.println("history.back();");
            out.println("</script>");
            return "forward:/register.html";
        }
        if (!registerPassword.equals("IOTDreamClub.")){
            model.addAttribute("msg","注册密码出错，无法注册");
            response.setContentType("text/html; charset=UTF-8"); //转码
            PrintWriter out = response.getWriter();
            out.flush();
            out.println("<script>");
            out.println("alert('注册密码出错，无法注册');");
            out.println("history.back();");
            out.println("</script>");
            return "forward:/register.html";
        }
        userService.register(username,password);
        model.addAttribute("msg","注册成功出错");
        return "redirect:/login.html";
    }

//    @RequestMapping("logout")
//    public ModelAndView logout(HttpSession session , ){
//
//    }
}
