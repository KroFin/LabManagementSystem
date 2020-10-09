package com.iotdreamclub.demo.service;

import com.iotdreamclub.demo.entity.Match;

import java.sql.Date;
import java.util.List;

public interface MatchService {
    void addNewMatchInfo(String matchName , Date matchTime);
    void createNewMatchBillTable(String matchName);
    List<Match> showListOfMatch();
}
