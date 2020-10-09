package com.iotdreamclub.demo.service.impl;

import com.iotdreamclub.demo.dao.MatchMapper;
import com.iotdreamclub.demo.entity.Match;
import com.iotdreamclub.demo.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchMapper matchMapper;

    @Override
    public void addNewMatchInfo(String matchName, Date matchTime) {
        matchMapper.addNewMatchInfo(matchName,matchTime);
    }

    @Override
    public void createNewMatchBillTable(String matchName) {
        matchMapper.createNewMatchBillTable(matchName);
    }

    @Override
    public List<Match> showListOfMatch() {
        return matchMapper.showListOfMatch();
    }
}
