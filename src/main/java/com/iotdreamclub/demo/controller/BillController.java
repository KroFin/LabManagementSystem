package com.iotdreamclub.demo.controller;

import com.iotdreamclub.demo.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;

//资金管理相关操作

@Controller
public class BillController {

    @Autowired
    private BillService billService;

    @RequestMapping("addNewRecord")
    @ResponseBody
    public String addNewRecord(float billMoney , int billType , String billComment , Date billTime){
        if (billType == 1){
            float billBalance = billService.sumBillMoney() + billMoney;
            billService.add(billMoney,billType,billComment,billTime,billBalance);
            return "success";
        }
        billMoney = billMoney - billMoney*2;
        float billBalance = billService.sumBillMoney() + billMoney;
        billService.add(billMoney,billType,billComment,billTime,billBalance);
        return "success";
    }
}
