package com.iotdreamclub.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.PageInfo;
import com.iotdreamclub.demo.entity.DeviceInfo;
import com.iotdreamclub.demo.entity.DeviceLend;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface DeviceMapper extends BaseMapper<DeviceInfo> {
    List<DeviceInfo> selectAll();
    void deCrease(Long deviceId);
    void addNewDevice(String deviceName , String deviceType , int deviceNumber , String deviceComment );
    void deleteDevice(Long deviceId);
    void changeDeviceInfo(String deviceName , String deviceType , Integer deviceNumber , String deviceComment , Long deviceId);
    DeviceInfo selectByDeviceId(Long deviceId);
    int checkDeviceNumber(Long deviceId);

    List<DeviceLend> selectAllLendInfo();
    void addDeviceLendInfo(Long lendId, String lendPeople , String lendDevice , Date lendTime , String lendOrderId);
    void deleteDeviceLendInfo(Long lendId);
    Long findDeviceIdByLendOrderId(Long lendOrderId);
    void addDeviceNumber(Long lendId);

    List<DeviceInfo> selectAllByPage();
    List<DeviceInfo> selectAllByKeyword(DeviceInfo deviceInfo);
}
