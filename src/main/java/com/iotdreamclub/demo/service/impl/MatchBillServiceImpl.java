package com.iotdreamclub.demo.service.impl;

import com.iotdreamclub.demo.dao.MatchBillMapper;
import com.iotdreamclub.demo.entity.Bill;
import com.iotdreamclub.demo.entity.MatchBillInfo;
import com.iotdreamclub.demo.service.BillService;
import com.iotdreamclub.demo.service.MatchBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class MatchBillServiceImpl implements MatchBillService {

    @Autowired
    private MatchBillMapper matchBillMapper;

    @Override
    public void add(float billMoney, int billType, String billComment, Date billTime, float billBalance) {
        matchBillMapper.add(billMoney,billType,billComment,billTime,billBalance);
    }

    @Override
    public List<MatchBillInfo> selectAll() {
        return matchBillMapper.selectAllBill();
    }

    @Override
    public float sumBillMoney() {
        return matchBillMapper.sumBillMoney();
    }

    @Override
    public void deleteBillInfo(Long billId) {
        matchBillMapper.deleteBillInfo(billId);
    }
}
