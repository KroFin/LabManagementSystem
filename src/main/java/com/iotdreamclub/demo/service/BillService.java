package com.iotdreamclub.demo.service;

import com.iotdreamclub.demo.entity.Bill;

import java.sql.Date;
import java.util.List;

public interface BillService {
    void add(float billMoney , int billType , String billComment , Date billTime , float billBalance);
    List<Bill> selectAll();
    float sumBillMoney();
}
