package com.iotdreamclub.demo.controller;

import com.iotdreamclub.demo.entity.DeviceInfo;
import com.iotdreamclub.demo.service.DeviceService;
import org.apache.ibatis.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;

//设备借入借出相关管理

@Controller
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping
    public ModelAndView queryAllDevice(Model model){
        List<DeviceInfo> deviceInfos = deviceService.selectAll();
        model.addAttribute("addDevice",deviceInfos);
        model.addAttribute("title","设备信息表");
        return new ModelAndView("index_device_table");
    }

    //管理员对设备相关信息的管理

    @RequestMapping("CDI/{deviceId}")
    public String change(Model model, @PathVariable Long deviceId){
        DeviceInfo deviceInfoRow = deviceService.selectByDeviceId(deviceId);
        model.addAttribute("deviceInfoRows",deviceInfoRow);
        return "edit";
    }

    @RequestMapping("change_device_info")
    @ResponseBody
    public String changeDeviceInfo(String deviceName, String deviceType, Integer deviceNumber, String deviceComment , Long deviceId , HttpServletResponse response){
        deviceService.changeDeviceInfo(deviceName,deviceType,deviceNumber,deviceComment,deviceId);
        try {
            response.sendRedirect("device_change");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return " ";
    }

    //弃用函数

    @RequestMapping("deCrease")
    @ResponseBody
    public String deCrease(long deviceId){
        try {
            deviceService.deCrease(deviceId);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "failed";
        }
    }

    //增加新的函数

    @RequestMapping("addNewDevice")
    @ResponseBody
    public String addNewDevice(String deviceName , String deviceType , int deviceNumber , String deviceComment){
        deviceService.addNewDevice(deviceName,deviceType,deviceNumber,deviceComment);
        return "success";
    }

    //删除设备

    @RequestMapping("deleteDevice/{deviceId}")
    @ResponseBody
    public String deleteDevice(@PathVariable Long deviceId ,HttpServletResponse response){
        deviceService.deleteDevice(deviceId);
        try {
            response.sendRedirect("/device_change");
        }catch (Exception e){
            e.printStackTrace();
        }
        return " ";
    }

    //普通用户借用设备登记

    @RequestMapping("borrowDevice/{deviceId}")
    public String borrowDevice(Model model, @PathVariable Long deviceId ,HttpServletResponse response , HttpSession session){
        try {
            DeviceInfo deviceInfoLend = deviceService.selectByDeviceId(deviceId);
            model.addAttribute("deviceInfoLends",deviceInfoLend);
            int deviceNumber = deviceService.checkDeviceNumber(deviceId);
            if (deviceNumber == 0){
                response.setContentType("text/html; charset=UTF-8"); //转码
                PrintWriter out = response.getWriter();
                out.flush();
                out.println("<script>");
                out.println("alert('设备数量不足');");
                out.println("history.back();");
                out.println("</script>");
                return "index_device_table";
            }
            return "device_lend_apply";
        }catch (Exception e){
            e.printStackTrace();
            return "failed";
        }
    }

    //借用设备后后台登记情况

    @RequestMapping("insertLendInfo")
    @ResponseBody
    public String insertLendInfo(String lendPeople , String lendDevice , Date lendTime , HttpServletResponse response , Long deviceId, HttpSession session , HttpServletRequest request){
        session.setAttribute("deviceId",deviceId);
        try {
            deviceService.addDeviceLendInfo(deviceId,lendPeople,lendDevice,lendTime);
            deviceService.deCrease(deviceId);
        }catch (Exception e){
            e.printStackTrace();
        }
        session = request.getSession(false);
        session.removeAttribute("deviceId");
        try {
            response.sendRedirect("index_device_table");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return " ";
    }

    @RequestMapping("returnDevice/{lendId}")
    @Transactional
    public String returnDevice(@PathVariable Long lendId){
        try {
            deviceService.deleteDeviceLendInfo(lendId);
            deviceService.addDeviceNumber(lendId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "index_device_lend";
    }
}
