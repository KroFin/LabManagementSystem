package com.iotdreamclub.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.iotdreamclub.demo.service.DeviceService;
import com.iotdreamclub.demo.service.UserService;
import org.apache.catalina.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class APIController {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private UserService userService;

    private int pageCount;

    private int pageLimit;

  @PostMapping("/getDeviceInfoAPI")
    public String getDeviceInfoAPI(HttpServletRequest request) throws IOException {
        StringBuffer jsonStr = RequestUtil.getRequestURL(request);

        FileWriter fileWriter = new FileWriter("log",true);
        fileWriter.write(String.valueOf(jsonStr));

        System.out.println(jsonStr);
        return "";
    }

    @RequestMapping("/returnLoginInfo")
    @ResponseBody
    public Map returnLoginInfo(){
        Map<String,Object> map = new HashMap<>();
        map.put("data",userService.selectAllLoginInfo());
        map.put("code",0);
        return map;
    }

    @RequestMapping(value = "carouselFileUpdate",method = RequestMethod.POST)
    @ResponseBody
    public String carouselFileUpdate(@RequestParam("file")MultipartFile file,
                                     HttpServletRequest request ,
                                     HttpServletResponse response){
        JSONObject res = new JSONObject();
        JSONObject resUrl = new JSONObject();

        File[] files = new File[3];

        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("/static/img");

        try {
            FileInputStream fileInputStream = new FileInputStream("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
