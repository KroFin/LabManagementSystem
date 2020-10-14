package com.iotdreamclub.demo.controller;

import com.iotdreamclub.demo.entity.Bill;
import com.iotdreamclub.demo.entity.Match;
import com.iotdreamclub.demo.service.MatchService;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@Controller
public class MatchController {

    @Autowired
    private MatchService matchService;

    @RequestMapping("index_match_management")
    public String showListOfMatch(Model model){
        List<Match> matchList = matchService.showListOfMatch();
        model.addAttribute("matchLists",matchList);
        return "index_match_management";
    }

    @RequestMapping("createNewMatchBillTable")
    @ResponseBody
    public void createNewMatchBillTable(String matchName , Date matchTime , HttpServletResponse response) throws IOException {
        String matchNameFormat = matchService.formatDateInfo(matchTime);
        matchService.createNewMatchBillTable(matchNameFormat);
        matchService.initializeTabel(matchNameFormat);
        matchService.addNewMatchInfo(matchName , matchTime , matchNameFormat);
        response.sendRedirect("/index_match_management");
    }

    @RequestMapping("showSelectedMatchTable/{matchNameFormat}")
    public String showSelectedMatchTable(@PathVariable String matchNameFormat , Model model , HttpServletRequest request ,HttpServletResponse response){
        List<Bill> bill = matchService.selectedMatchTablle(matchNameFormat);
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie1 : cookies){
                if (cookie1.getName().equals("matchNameFormat")){
                    cookie1.setMaxAge(0);
                }
            }
        }
        Cookie cookie = new Cookie("matchNameFormat",matchNameFormat);
        cookie.setMaxAge(5 * 60);
        response.addCookie(cookie);
        model.addAttribute("bills",bill);
        return "match_table_witch_selected";
    }

    @RequestMapping("addNewMatchRecord/{matchNameFormat}")
    @ResponseBody
    public String addNewMatchRecord(@CookieValue(value = "matchNameFormat") @PathVariable String matchNameFormat ,
                                    float billMoney ,
                                    int billType ,
                                    String billComment ,
                                    Date billTime ,
                                    HttpServletResponse response) throws IOException {
        System.out.println(matchNameFormat);
        if (billType == 1){
            float billBalance = matchService.sumBillMoney(matchNameFormat) + billMoney;
            System.out.println(billBalance);
            //matchService.add(billMoney,billType,billComment,billTime,billBalance);
            response.sendRedirect("/index_match_management/"+matchNameFormat);
            return "success";
        }
        billMoney = billMoney - billMoney*2;
        float billBalance = matchService.sumBillMoney(matchNameFormat) + billMoney;
        matchService.add(billMoney,billType,billComment,billTime,billBalance);
        response.sendRedirect("/showSelectedMatchTable");
        return "success";
    }
}
