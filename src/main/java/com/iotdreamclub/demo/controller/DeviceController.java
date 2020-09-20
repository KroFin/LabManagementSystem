package com.iotdreamclub.demo.controller;

import com.iotdreamclub.demo.entity.DeviceInfo;
import com.iotdreamclub.demo.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

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

    @RequestMapping("add_device_number")
    @ResponseBody
    public String addDeviceNumber(long deviceId){
        try {
            deviceService.addDeviceNumber(deviceId);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "failed";
        }
    }

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

    @RequestMapping("addNewDevice")
    @ResponseBody
    public String addNewDevice(String deviceName , String deviceType , int deviceNumber , String deviceComment){
        deviceService.addNewDevice(deviceName,deviceType,deviceNumber,deviceComment);
        return "success";
    }

    @RequestMapping("deleteDevice")
    @ResponseBody
    public String deleteDevice(long deviceId){
        try {
            deviceService.deleteDevice(deviceId);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "failed";
        }
    }

    @RequestMapping("borrowDevice/{deviceId}")
    public String borrowDevice(Model model, @PathVariable Long deviceId){
        try {
            deviceService.deCrease(deviceId);
            DeviceInfo deviceInfoLend = deviceService.selectByDeviceId(deviceId);
            model.addAttribute("deviceInfoLends",deviceInfoLend);
            return "device_lend_apply";
        }catch (Exception e){
            e.printStackTrace();
            return "failed";
        }
    }

    @RequestMapping("insertLendInfo")
    @ResponseBody
    public String insertLendInfo(String lendPeople , String lendDevice , Date lendTime ,HttpServletResponse response){
        deviceService.addDeviceLendInfo(lendPeople,lendDevice,lendTime);
        try {
            response.sendRedirect("device_change");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return " ";
    }
}
