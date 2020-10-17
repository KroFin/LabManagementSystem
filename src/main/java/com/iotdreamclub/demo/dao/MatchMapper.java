package com.iotdreamclub.demo.dao;

import com.iotdreamclub.demo.entity.Bill;
import com.iotdreamclub.demo.entity.Match;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface MatchMapper {
    void addNewMatchInfo(String matchName , Date matchTime ,String matchNameFormat);
    void createNewMatchBillTable(String matchNameFormat);
    List<Match> showListOfMatch();

    List<Bill> selectedMatchTablle(String matchNameFormat);
    void deleteMatchTable(String matchNameFormat);
    void deleteMatchInfo(String matchNameFormat);

    void initializeTabel(String matchNameFormat);

    void add(String matchNameFormat,float billMoney , int billType , String billComment , Date billTime , float billBalance);
    float sumBillMoney(String matchNameFormat);
    void deleteMatchBillInfo(String matchNameFormat ,Long billId);
}
