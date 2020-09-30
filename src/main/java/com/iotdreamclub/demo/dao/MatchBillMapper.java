package com.iotdreamclub.demo.dao;

import com.iotdreamclub.demo.entity.Bill;
import com.iotdreamclub.demo.entity.MatchBillInfo;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface MatchBillMapper {
    void add(float billMoney , int billType , String billComment , Date billTime , float billBalance);
    List<MatchBillInfo> selectAllBill();
    float sumBillMoney();
    void deleteBillInfo(Long billId);
}
