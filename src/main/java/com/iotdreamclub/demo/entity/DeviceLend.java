package com.iotdreamclub.demo.entity;


public class DeviceLend {

  private String lendPeople;
  private String lendDevice;
  private java.sql.Date lendTime;


  public String getLendPeople() {
    return lendPeople;
  }

  public void setLendPeople(String lendPeople) {
    this.lendPeople = lendPeople;
  }


  public String getLendDevice() {
    return lendDevice;
  }

  public void setLendDevice(String lendDevice) {
    this.lendDevice = lendDevice;
  }


  public java.sql.Date getLendTime() {
    return lendTime;
  }

  public void setLendTime(java.sql.Date lendTime) {
    this.lendTime = lendTime;
  }

}
