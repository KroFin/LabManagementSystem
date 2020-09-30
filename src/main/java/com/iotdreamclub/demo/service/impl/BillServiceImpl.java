package com.iotdreamclub.demo.service.impl;

import com.iotdreamclub.demo.dao.BillMapper;
import com.iotdreamclub.demo.entity.Bill;
import com.iotdreamclub.demo.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillMapper billMapper;

    @Override
    public void add(float billMoney, int billType, String billComment, Date billTime, float billBalance) {
        billMapper.add(billMoney,billType,billComment,billTime,billBalance);
    }

    @Override
    public List<Bill> selectAll() {
        return billMapper.selectAllBill();
    }

    @Override
    public float sumBillMoney() {
        return billMapper.sumBillMoney();
    }

    @Override
    public void deleteBillInfo(Long deviceId) {
        billMapper.deleteBillInfo(deviceId);
    }
}
