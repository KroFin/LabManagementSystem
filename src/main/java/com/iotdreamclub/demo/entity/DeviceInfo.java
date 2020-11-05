package com.iotdreamclub.demo.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("tb_device_info")
public class DeviceInfo {

  private long deviceId;
  private String deviceName;
  private String deviceType;
  private int deviceNumber;
  private String deviceComment;

  private DeviceLend deviceLend;

  public DeviceLend getDeviceLend() {
    return deviceLend;
  }

  public void setDeviceLend(DeviceLend deviceLend) {
    this.deviceLend = deviceLend;
  }

  @Override
  public String toString() {
    return "DeviceInfo{" +
            "deviceId=" + deviceId +
            ", deviceName='" + deviceName + '\'' +
            ", deviceType='" + deviceType + '\'' +
            ", deviceNumber=" + deviceNumber +
            ", deviceComment='" + deviceComment + '\'' +
            '}';
  }

  public long getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(long deviceId) {
    this.deviceId = deviceId;
  }


  public String getDeviceName() {
    return deviceName;
  }

  public void setDeviceName(String deviceName) {
    this.deviceName = deviceName;
  }


  public String getDeviceType() {
    return deviceType;
  }

  public void setDeviceType(String deviceType) {
    this.deviceType = deviceType;
  }


  public long getDeviceNumber() {
    return deviceNumber;
  }

  public void setDeviceNumber(int deviceNumber) {
    this.deviceNumber = deviceNumber;
  }


  public String getDeviceComment() {
    return deviceComment;
  }

  public void setDeviceComment(String deviceComment) {
    this.deviceComment = deviceComment;
  }

}
