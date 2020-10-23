package com.iotdreamclub.demo.service;

import net.ipip.ipdb.CityInfo;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public interface FunctionService {
    String getCookieValue(String cookieName , HttpServletRequest request);
    void getDeviceInfoAPi();
    String getRemoteAddr(HttpServletRequest request);

    CityInfo getAddrInfoFromDB(String addr);
}
