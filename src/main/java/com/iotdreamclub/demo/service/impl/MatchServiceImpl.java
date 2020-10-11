package com.iotdreamclub.demo.service.impl;

import com.iotdreamclub.demo.dao.MatchMapper;
import com.iotdreamclub.demo.entity.Match;
import com.iotdreamclub.demo.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {

    public static final String dateFormat = "yyyyMMddHHmmss";

    @Autowired
    private MatchMapper matchMapper;

    @Override
    public void addNewMatchInfo(String matchName, Date matchTime , String matchNameFormat) {
        matchMapper.addNewMatchInfo(matchName,matchTime,matchNameFormat);
    }

    @Override
    public void createNewMatchBillTable(String matchName) {
        matchMapper.createNewMatchBillTable(matchName);
    }

    @Override
    public List<Match> showListOfMatch() {
        return matchMapper.showListOfMatch();
    }

    @Override
    public void initializeTabel(String matchNameFormat) {
        matchMapper.initializeTabel(matchNameFormat);
    }

    @Override
    public String formatDateInfo(Date matchTime) {
        DateFormat dateFormat01 = new SimpleDateFormat(dateFormat);
        return dateFormat01.format(matchTime);
    }
}
