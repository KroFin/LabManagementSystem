package com.iotdreamclub.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.iotdreamclub.demo.entity.DeviceInfo;
import com.iotdreamclub.demo.entity.DeviceLend;

import java.sql.Date;
import java.util.List;

public interface DeviceService extends IService<DeviceInfo> {
    List<DeviceInfo> selectAll();
    void deCrease(Long deviceId);
    void addNewDevice(String deviceName , String deviceType , int deviceNumber , String deviceComment);
    void deleteDevice(Long deviceId);
    void changeDeviceInfo(String deviceName , String deviceType , Integer deviceNumber , String deviceComment , Long deviceId);
    DeviceInfo selectByDeviceId(Long deviceId);
    int checkDeviceNumber(Long deviceId);

    List<DeviceLend> selectAllLendInfo();
    void addDeviceLendInfo(Long lendId, String lendPeople , String lendDevice , Date lendTime, String lendOrderId);
    Long findDeviceIdByLendOrderId(Long lendOrderId);
    void deleteDeviceLendInfo(Long lendId);
    void addDeviceNumber(Long lendId);
}
