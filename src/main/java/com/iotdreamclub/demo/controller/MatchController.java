package com.iotdreamclub.demo.controller;

import com.iotdreamclub.demo.entity.Match;
import com.iotdreamclub.demo.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Date;
import java.util.List;

@Controller
public class MatchController {

    @Autowired
    private MatchService matchService;

    @RequestMapping("showListOfMatch")
    public void showListOfMatch(Model model){
        List<Match> matchList = matchService.showListOfMatch();
        model.addAttribute("matchLists",matchList);
    }

    @RequestMapping("createNewMatchBillTable")
    public void createNewMatchBillTable(String matchName , Date matchTime){
        String matchNameFormat = matchService.formatDateInfo(matchTime);
        matchService.createNewMatchBillTable(matchNameFormat);
        matchService.initializeTabel(matchNameFormat);
        matchService.addNewMatchInfo(matchName , matchTime , matchNameFormat);
    }
}
