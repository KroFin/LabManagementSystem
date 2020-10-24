package com.iotdreamclub.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.iotdreamclub.demo.service.DeviceService;
import org.apache.catalina.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @RequestMapping(value = "carouselFileUpdate",method = RequestMethod.POST)
    @ResponseBody
    public String carouselFileUpdate(@RequestParam("file")MultipartFile file,
                                     HttpServletRequest request ,
                                     HttpServletResponse response){
        JSONObject res = new JSONObject();
        JSONObject resUrl = new JSONObject();
        return null;
    }
}
