package com.iotdreamclub.demo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//主要页面管理

@Controller
public class PageController {


    //默认页面

    @RequestMapping("login")
    public String login(){
        return "login";
    }

    @RequestMapping("register")
    public String register(){
        return "register";
    }

    @RequestMapping("support")
    public String support(){
        return "support";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session, Model model , HttpServletRequest request ){
        Subject subject = SecurityUtils.getSubject();
        session = request.getSession(false);
        session.removeAttribute("username");
        session.removeAttribute("limit");
        session.removeAttribute("loginUser");
        subject.logout();
        return "login";
    }
}
