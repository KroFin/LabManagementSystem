package com.iotdreamclub.demo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class PageController {


    @RequestMapping("/")
    public String DefaultPage(){
        return "login";
    }

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
    public String logout(HttpSession session, Model model){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }
}
