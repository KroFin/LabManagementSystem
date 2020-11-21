package com.iotdreamclub.demo.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.iotdreamclub.demo.dao.MatchBillMapper;
import com.iotdreamclub.demo.service.BillService;
import com.iotdreamclub.demo.service.MatchBillService;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
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
            response.sendRedirect("/economics_management?pageNum=1");
            return "success";
        }
        billMoney = billMoney - billMoney*2;
        float billBalance = billService.sumBillMoney() + billMoney;
        billService.add(billMoney,billType,billComment,billTime,billBalance);
        response.sendRedirect("/economics_management?pageNum=1");
        return "success";
    }

    @RequestMapping("deleteBillInfo/{billId}")
    @ResponseBody
    public String deleteBillInfo(@PathVariable Long billId , HttpServletResponse response , HttpSession session) throws IOException {

        String userLimit = String.valueOf(session.getAttribute("limit"));
        System.out.println(userLimit);

        if(String.valueOf(session.getAttribute("limit")).equals("2") || String.valueOf(session.getAttribute("limit")).equals("3")){
            response.setContentType("text/html; charset=UTF-8"); //转码
            PrintWriter out = response.getWriter();
            out.flush();
            out.println("<script>");
            out.println("alert('权限不足无法操作');");
            out.println("history.back();");
            out.println("</script>");
            response.sendRedirect("/economics_management?pageNum=1");
        }else {
            billService.deleteBillInfo(billId);
        }
        try {
            response.sendRedirect("/economics_management?pageNum=1");
        }catch (Exception e){
            e.printStackTrace();
        }
        return " ";
    }

}
