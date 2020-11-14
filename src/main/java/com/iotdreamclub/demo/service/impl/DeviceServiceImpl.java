package com.iotdreamclub.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iotdreamclub.demo.dao.DeviceMapper;
import com.iotdreamclub.demo.entity.DeviceInfo;
import com.iotdreamclub.demo.entity.DeviceLend;
import com.iotdreamclub.demo.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;

@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper,DeviceInfo> implements DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public List<DeviceInfo> selectAll() {
        return deviceMapper.selectAll();
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
    public int checkDeviceNumber(Long deviceId) {
        return deviceMapper.checkDeviceNumber(deviceId);
    }

    @Override
    public List<DeviceLend> selectAllLendInfo() {
        return deviceMapper.selectAllLendInfo();
    }

    @Override
    public void addDeviceLendInfo(Long lendId, String lendPeople, String lendDevice, Date lendTime, String lendOrderId) {
        deviceMapper.addDeviceLendInfo(lendId,lendPeople,lendDevice,lendTime,lendOrderId);
    }

    @Override
    public Long findDeviceIdByLendOrderId(Long lendOrderId) {
        return deviceMapper.findDeviceIdByLendOrderId(lendOrderId);
    }

    @Override
    public void deleteDeviceLendInfo(Long lendId) {
        deviceMapper.deleteDeviceLendInfo(lendId);
    }

    @Override
    public void addDeviceNumber(Long lendId) {
        deviceMapper.addDeviceNumber(lendId);
    }

    @Override
    public List<DeviceInfo> selectAllByPage(Integer pageNum, Integer pageSize) {
        return deviceMapper.selectAllByPage();
    }

    @Override
    public List<DeviceInfo> selectAllByKeyword(String searchContent, String searchKeywords) {
        DeviceInfo deviceInfo = new DeviceInfo();
        if (searchKeywords.equals("deviceName")){
            deviceInfo.setDeviceName(searchContent);
        }else if (searchKeywords.equals("deviceType")){
            deviceInfo.setDeviceType(searchContent);
        }
        return deviceMapper.selectAllByKeyword(deviceInfo);
    }
}
