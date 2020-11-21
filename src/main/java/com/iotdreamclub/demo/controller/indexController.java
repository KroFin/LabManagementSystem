package com.iotdreamclub.demo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.iotdreamclub.demo.entity.*;
import com.iotdreamclub.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    
    @Autowired
    private FileService fileService;

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
        List<UserLoginInfo> userLoginInfos = userService.selectAllLoginInfo();
        for (User user : users){
            String userLimit = user.getUserLimit();
            if (userLimit.equals("1")){
                userLimit = "管理员";
            }
        }
        model.addAttribute("Members",userService.selectAll());
        model.addAttribute("userLoginInfo",userLoginInfos);
        return "index_member_management";
    }

    //设备管理

    @RequestMapping("device_change")
    public String deviceTable(Model model){
        model.addAttribute("deviceInfo", deviceService.selectAll());
        return "index_device_table";
    }

    @RequestMapping("showDeviceTable")
    public String showDeviceTable(Model model ,
                                  @RequestParam(defaultValue = "1")Integer pageNum ,
                                  @RequestParam(defaultValue = "10")Integer pageSize){
        if(pageNum == null){
            pageNum = 1;   //设置默认当前页
        }
        if(pageNum <= 0){
            pageNum = 1;
        }
        if(pageSize == null){
            pageSize = 10;    //设置默认每页显示的数据数
        }
        System.out.println("当前页是："+pageNum+"显示条数是："+pageSize);

        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum,pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<DeviceInfo> deviceInfo = deviceService.selectAllByPage(pageNum,pageSize);
            System.out.println("分页数据："+deviceInfo);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<DeviceInfo> pageInfo = new PageInfo<>(deviceInfo,pageSize);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo",pageInfo);
            model.addAttribute("deviceInfos",deviceInfo);
        }finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        //5.设置返回的jsp/html等前端页面
        // thymeleaf默认就会拼串classpath:/templates/xxxx.html
        return "index_device_table";
    }

    //实验室经费管理

    @RequestMapping("economics_management")
    public String economicsManagement(Model model,
                                      @RequestParam(defaultValue = "1")Integer pageNum ,
                                      @RequestParam(defaultValue = "10")Integer pageSize){
        if(pageNum == null){
            pageNum = 1;
        }
        if(pageNum <= 0){
            pageNum = 1;
        }
        if(pageSize == null){
            pageSize = 10;
        }
        System.out.println("当前页是："+pageNum+"显示条数是："+pageSize);

        PageHelper.startPage(pageNum,pageSize);

        try {
            List<Bill> bills = billService.selectAll();
            System.out.println("分页数据："+ bills);
            PageInfo<Bill> pageInfo = new PageInfo<>(bills,pageSize);
            model.addAttribute("pageInfo",pageInfo);
            model.addAttribute("bill",bills);
        }finally {
            PageHelper.clearPage();
        }

        return "index_economics_management";
    }

    //个人信息管理

    @RequestMapping("persional_info_change/{username}")
    public String persionalInfoChange(Model model , @PathVariable String username,HttpSession session){
        String loginUsername = String.valueOf(session.getAttribute("username"));
        model.addAttribute("loginUsers", userService.selectUserByName(loginUsername));
        return "index_persional_info_change";
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

    @RequestMapping("toFileUploadPage")
    public String toFileUploadPage(Model model){
        List<FileUpload> fileUpload = fileService.selectAllFile();
        model.addAttribute("fileUploads",fileUpload);
        return "index_file_upload";
    }

}
