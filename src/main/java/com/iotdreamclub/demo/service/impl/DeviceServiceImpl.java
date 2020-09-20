package com.iotdreamclub.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iotdreamclub.demo.dao.DeviceMapper;
import com.iotdreamclub.demo.entity.DeviceInfo;
import com.iotdreamclub.demo.entity.DeviceLend;
import com.iotdreamclub.demo.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class DeviceServiceImpl  extends ServiceImpl<DeviceMapper,DeviceInfo> implements DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public List<DeviceInfo> selectAll() {
        return deviceMapper.selectAll();
    }

    @Override
    public void addDeviceNumber(Long deviceId) {
        deviceMapper.addDeviceNumber(deviceId);
    }

    @Override
    public void deCrease(Long deviceId) {
        deviceMapper.deCrease(deviceId);
    }

    @Override
    public void addNewDevice(String deviceName, String deviceType, int deviceNumber, String deviceComment) {
        deviceMapper.addNewDevice(deviceName,deviceType,deviceNumber,deviceComment);
    }

    @Override
    public void deleteDevice(Long deviceId) {
        deviceMapper.deleteDevice(deviceId);
    }

    @Override
    public void changeDeviceInfo(String deviceName, String deviceType, Integer deviceNumber, String deviceComment , Long deviceId) {
        deviceMapper.changeDeviceInfo(deviceName,deviceType,deviceNumber,deviceComment,deviceId);
    }

    @Override
    public DeviceInfo selectByDeviceId(Long deviceId) {
        return deviceMapper.selectByDeviceId(deviceId);
    }

    @Override
    public List<DeviceLend> selectAllLendInfo() {
        return deviceMapper.selectAllLendInfo();
    }

    @Override
    public void addDeviceLendInfo(String lendPeople, String lendDevice, Date lendTime) {
        deviceMapper.addDeviceLendInfo(lendPeople,lendDevice,lendTime);
    }

}
