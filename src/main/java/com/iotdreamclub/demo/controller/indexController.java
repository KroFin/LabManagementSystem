package com.iotdreamclub.demo.controller;

import com.iotdreamclub.demo.entity.*;
import com.iotdreamclub.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

//主页下属页面跳转管理

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

    @Autowired
    private MatchBillService matchBillService;

    @RequestMapping("index")
    public String hello(HttpSession session , Model model){
        String limit = String.valueOf(session.getAttribute("limit"));
        List<RoleModule> roleModules = roleModuleService.selectAllByLimit(limit);
        model.addAttribute("rolelists",roleModules);
        return "index";
    }

    //人员管理

    @RequestMapping("member_management")
    public String memberManagement(Model model){
        List<User> users = userService.selectAll();
        for (User user : users){
            String userLimit = user.getUserLimit();
            if (userLimit.equals("1")){
                userLimit = "管理员";
            }
        }
        model.addAttribute("Members",userService.selectAll());
        return "index_member_management";
    }

    //设备管理

    @RequestMapping("device_change")
    public String deviceTable(Model model){
        model.addAttribute("deviceInfo", deviceService.selectAll());
        return "index_device_table";
    }

    //个人信息管理

    @RequestMapping("persional_info_change/{username}")
    public String persionalInfoChange(Model model , @PathVariable String username,HttpSession session){
        String loginUsername = String.valueOf(session.getAttribute("username"));
        model.addAttribute("loginUsers", userService.selectUserByName(loginUsername));
        return "index_persional_info_change";
    }

    //实验室经费管理

    @RequestMapping("economics_management")
    public String economicsManagement(Model model){
        List<Bill> bills = billService.selectAll();
        model.addAttribute("bill",bills);
        return "index_economics_management";
    }

    //竞赛经费管理

    @RequestMapping("index_match_economics_management")
    public String matchEconomicsManagement(Model model){
        List<MatchBillInfo> matchBillInfo = matchBillService.selectAll();
        model.addAttribute("bill",matchBillInfo);
        return "index_match_economics_management";
    }

    //经费消耗表单打印

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
