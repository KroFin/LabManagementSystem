package com.iotdreamclub.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.iotdreamclub.demo.entity.Bill;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface BillMapper extends BaseMapper<Bill> {
    void add(float billMoney , int billType , String billComment , Date billTime , float billBalance);
    List<Bill> selectAllBill();
    float sumBillMoney();
}
