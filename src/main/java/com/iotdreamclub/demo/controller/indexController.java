package com.iotdreamclub.demo.controller;

import com.iotdreamclub.demo.entity.Bill;
import com.iotdreamclub.demo.entity.DeviceLend;
import com.iotdreamclub.demo.entity.RoleModule;
import com.iotdreamclub.demo.service.BillService;
import com.iotdreamclub.demo.service.DeviceService;
import com.iotdreamclub.demo.service.RoleModuleService;
import com.iotdreamclub.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class indexController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleModuleService roleModuleService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private BillService billService;

    @RequestMapping("index")
    public String hello(HttpSession session , Model model){
        String limit = String.valueOf(session.getAttribute("limit"));
        List<RoleModule> roleModules = roleModuleService.selectAllByLimit(limit);
        model.addAttribute("rolelists",roleModules);
        return "index";
    }

    @RequestMapping("member_management")
    public String memberManagement(Model model){
        model.addAttribute("Members",userService.selectAll());
        return "index_member_management";
    }


    @RequestMapping("device_change")
    public String deviceTable(Model model){
        model.addAttribute("deviceInfo", deviceService.selectAll());
        return "index_device_table";
    }

    @RequestMapping("persional_info_change/{username}")
    public String persionalInfoChange(Model model , @PathVariable String username,HttpSession session){
        String loginUsername = String.valueOf(session.getAttribute("username"));
        model.addAttribute("loginUsers", userService.selectUserByName(loginUsername));
        return "index_persional_info_change";
    }

    @RequestMapping("economics_management")
    public String economicsManagement(Model model){
        List<Bill> bills = billService.selectAll();
        model.addAttribute("bill",bills);
        return "index_economics_management";
    }

    @RequestMapping("select_all_lend_info")
    public String selectAllLendInfo(Model model){
        List<DeviceLend> deviceLend = deviceService.selectAllLendInfo();
        model.addAttribute("deviceLends",deviceLend);
        return "index_device_lend";
    }

//    @RequestMapping("findList")
//    public String roleList( , ){
//
//        return "index";
//    }
}