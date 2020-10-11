package com.iotdreamclub.demo.service;

import com.iotdreamclub.demo.entity.Match;

import java.sql.Date;
import java.util.List;

public interface MatchService {
    void addNewMatchInfo(String matchName , Date matchTime , String matchNameFormat);
    void createNewMatchBillTable(String matchName);
    List<Match> showListOfMatch();

    void initializeTabel(String matchNameFormat);

    String formatDateInfo(Date matchTime);
}
