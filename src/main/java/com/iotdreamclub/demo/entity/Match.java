package com.iotdreamclub.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("tb_match")
public class Match {

  private long matchId;
  private String matchName;
  private String matchNameFormat;
  private java.sql.Date matchTime;


  public long getMatchId() {
    return matchId;
  }

  public void setMatchId(long matchId) {
    this.matchId = matchId;
  }


  public String getMatchName() {
    return matchName;
  }

  public void setMatchName(String matchName) {
    this.matchName = matchName;
  }


  public String getMatchNameFormat() {
    return matchNameFormat;
  }

  public void setMatchNameFormat(String matchNameFormat) {
    this.matchNameFormat = matchNameFormat;
  }


  public java.sql.Date getMatchTime() {
    return matchTime;
  }

  public void setMatchTime(java.sql.Date matchTime) {
    this.matchTime = matchTime;
  }

}
