package com.iotdreamclub.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Properties;

public class MailController {
    @Autowired
    private JavaMailSender javaMailSender;

    private String userTo;
    private String userFrom = "localhost";

    @RequestMapping("mailSend")
    public String mailSend(){
        Properties properties = System.getProperties();
        properties.setProperty("",userFrom);
        return null;
    }
}
