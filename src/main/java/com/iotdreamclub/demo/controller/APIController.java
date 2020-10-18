package com.iotdreamclub.demo.controller;

import org.apache.catalina.util.RequestUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class APIController {
    @PostMapping("/getDeviceInfoAPI")
    public String getDeviceInfoAPI(HttpServletRequest request){
        StringBuffer jsonStr = RequestUtil.getRequestURL(request);
        System.out.println(jsonStr);
        return "";
    }
}
