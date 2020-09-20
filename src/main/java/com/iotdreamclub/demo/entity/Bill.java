package com.iotdreamclub.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("tb_bill_info")
public class Bill {

  private long billId;
  private double billMoney;
  private long billType;
  private String billComment;
  private java.sql.Date billTime;
  private double billBalance;


  public long getBillId() {
    return billId;
  }

  public void setBillId(long billId) {
    this.billId = billId;
  }


  public double getBillMoney() {
    return billMoney;
  }

  public void setBillMoney(double billMoney) {
    this.billMoney = billMoney;
  }


  public long getBillType() {
    return billType;
  }

  public void setBillType(long billType) {
    this.billType = billType;
  }


  public String getBillComment() {
    return billComment;
  }

  public void setBillComment(String billComment) {
    this.billComment = billComment;
  }


  public java.sql.Date getBillTime() {
    return billTime;
  }

  public void setBillTime(java.sql.Date billTime) {
    this.billTime = billTime;
  }


  public double getBillBalance() {
    return billBalance;
  }

  public void setBillBalance(double billBalance) {
    this.billBalance = billBalance;
  }

}
