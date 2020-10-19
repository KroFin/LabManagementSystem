package com.iotdreamclub.demo.controller;

import com.iotdreamclub.demo.service.DeviceService;
import org.apache.catalina.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class APIController {

    @Autowired
    private DeviceService deviceService;

    @PostMapping("/getDeviceInfoAPI")
    public String getDeviceInfoAPI(HttpServletRequest request){
        StringBuffer jsonStr = RequestUtil.getRequestURL(request);
        System.out.println(jsonStr);
        return "";
    }
}
