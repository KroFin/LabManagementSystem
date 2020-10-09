package com.iotdreamclub.demo.dao;

import com.iotdreamclub.demo.entity.Match;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface MatchMapper {
    void addNewMatchInfo(String matchName , Date matchTime);
    void createNewMatchBillTable(String matchName);
    List<Match> showListOfMatch();
}
