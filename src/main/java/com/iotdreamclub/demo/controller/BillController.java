package com.iotdreamclub.demo.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.iotdreamclub.demo.dao.MatchBillMapper;
import com.iotdreamclub.demo.service.BillService;
import com.iotdreamclub.demo.service.MatchBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

//资金管理相关操作

@Controller
public class BillController {

    @Autowired
    private BillService billService;

    @Autowired
    private MatchBillService matchBillService;

    @RequestMapping("addNewRecord")
    @ResponseBody
    public String addNewRecord(float billMoney , int billType , String billComment , Date billTime , HttpServletResponse response) throws IOException {
        if (billType == 1){
            float billBalance = billService.sumBillMoney() + billMoney;
            billService.add(billMoney,billType,billComment,billTime,billBalance);
            response.sendRedirect("/economics_management");
            return "success";
        }
        billMoney = billMoney - billMoney*2;
        float billBalance = billService.sumBillMoney() + billMoney;
        billService.add(billMoney,billType,billComment,billTime,billBalance);
        response.sendRedirect("/economics_management");
        return "success";
    }

    @RequestMapping("addNewMatchRecord")
    @ResponseBody
    public String addNewMatchRecord(float billMoney , int billType , String billComment , Date billTime , HttpServletResponse response) throws IOException {
        if (billType == 1){
            float billBalance = matchBillService.sumBillMoney() + billMoney;
            matchBillService.add(billMoney,billType,billComment,billTime,billBalance);
            response.sendRedirect("/index_match_economics_management");
            return "success";
        }
        billMoney = billMoney - billMoney*2;
        float billBalance = matchBillService.sumBillMoney() + billMoney;
        matchBillService.add(billMoney,billType,billComment,billTime,billBalance);
        response.sendRedirect("/index_match_economics_management");
        return "success";
    }

    @RequestMapping("deleteBillInfo/{billId}")
    @ResponseBody
    public String deleteBillInfo(@PathVariable Long billId , HttpServletResponse response){
        billService.deleteBillInfo(billId);
        try {
            response.sendRedirect("/economics_management");
        }catch (Exception e){
            e.printStackTrace();
        }
        return " ";
    }

    @RequestMapping("deleteMatchBillInfo/{matchBillId}")
    @ResponseBody
    public String deleteMatchBillInfo(@PathVariable Long matchBillId , HttpServletResponse response){
        matchBillService.deleteBillInfo(matchBillId);
        try {
            response.sendRedirect("/index_match_economics_management");
        }catch (Exception e){
            e.printStackTrace();
        }
        return " ";
    }

}
