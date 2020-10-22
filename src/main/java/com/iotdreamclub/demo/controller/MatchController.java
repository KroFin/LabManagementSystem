package com.iotdreamclub.demo.controller;

import com.iotdreamclub.demo.entity.Bill;
import com.iotdreamclub.demo.entity.Match;
import com.iotdreamclub.demo.service.MatchService;
import org.apache.ibatis.annotations.Param;
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

/**
 * 本控制类负责新的比赛数据表的添加和修改
 * 于2020/10/16日创建并完成所有功能函数的编写
 */

@Controller
public class MatchController {

    @Autowired
    private MatchService matchService;

    /**
     *
     * 返回整个比赛目录列表
     *
     */

    @RequestMapping("index_match_management")
    public String showListOfMatch(Model model){
        List<Match> matchList = matchService.showListOfMatch();
        model.addAttribute("matchLists",matchList);
        return "index_match_management";
    }

    //创建新的比赛数据表

    @RequestMapping("createNewMatchBillTable")
    @ResponseBody
    public void createNewMatchBillTable(String matchName , Date matchTime , HttpServletResponse response) throws IOException {
        String matchNameFormat = matchService.formatDateInfo(matchTime);
        matchService.createNewMatchBillTable(matchNameFormat);
        matchService.initializeTabel(matchNameFormat);
        matchService.addNewMatchInfo(matchName , matchTime , matchNameFormat);
    }

    //删除比赛数据表

    @RequestMapping("deleteMatchTable/{matchNameFormat}")
    public void deleteMatchTable(@PathVariable String matchNameFormat ,HttpServletResponse response){
        matchService.deleteMatchTable(matchNameFormat);
        matchService.deleteMatchInfo(matchNameFormat);
        try {
            response.sendRedirect("/index_match_management");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //进入选中的比赛表进行编辑

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
        cookie.setMaxAge(60 * 60);
        cookie.setPath("/");
        response.addCookie(cookie);
        model.addAttribute("bills",bill);
        return "match_table_witch_selected";
    }

    //添加选中的表中的数据

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
            matchService.add(matchNameFormat,billMoney,billType,billComment,billTime,billBalance);
            response.sendRedirect("/showSelectedMatchTable/"+matchNameFormat);
            return "success";
        }
        billMoney = billMoney - billMoney*2;
        float billBalance = matchService.sumBillMoney(matchNameFormat) + billMoney;
        matchService.add(matchNameFormat,billMoney,billType,billComment,billTime,billBalance);
        response.sendRedirect("/showSelectedMatchTable/"+matchNameFormat);
        return "success";
    }

    //删除选中的表中的一行数据

    @RequestMapping("deleteMatchBillInfo/{bill_id}")
    @ResponseBody
    public void deleteMatchBillInfo(@PathVariable Long bill_id ,
                                    String matchNameFormat,
                                    HttpServletResponse response,
                                    HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies){
            if (cookie.getName().equals("matchNameFormat")){
                matchNameFormat = cookie.getValue();
            }
        }
        matchService.deleteMatchBillInfo(matchNameFormat, bill_id);
        try {
            response.sendRedirect("/showSelectedMatchTable/"+matchNameFormat);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
