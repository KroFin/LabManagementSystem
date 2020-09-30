package com.iotdreamclub.demo.service;

import com.iotdreamclub.demo.entity.Bill;
import com.iotdreamclub.demo.entity.MatchBillInfo;

import java.sql.Date;
import java.util.List;

public interface MatchBillService {
    void add(float billMoney, int billType, String billComment, Date billTime, float billBalance);
    List<MatchBillInfo> selectAll();
    float sumBillMoney();
    void deleteBillInfo(Long billId);
}
