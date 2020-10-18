package com.iotdreamclub.demo.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public interface FunctionService {
    String getCookieValue(String cookieName , HttpServletRequest request);
    void getDeviceInfoAPi();
}
