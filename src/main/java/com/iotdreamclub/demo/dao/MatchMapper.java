package com.iotdreamclub.demo.dao;

import com.iotdreamclub.demo.entity.Match;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface MatchMapper {
    void addNewMatchInfo(String matchName , Date matchTime ,String matchNameFormat);
    void createNewMatchBillTable(String matchNameFormat);
    List<Match> showListOfMatch();

    void initializeTabel(String matchNameFormat);
}
