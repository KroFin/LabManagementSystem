package com.iotdreamclub.demo.service.impl;

import com.iotdreamclub.demo.service.FunctionService;
import net.ipip.ipdb.City;
import net.ipip.ipdb.CityInfo;
import net.ipip.ipdb.IPFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class FunctionServiceImpl implements FunctionService {

    @Autowired
    ResourceLoader resourceLoader;

    public static final String dateFormat = "yyyyMMddHHmmss";

    @Override
    public String getCookieValue(String cookieName , HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies){
            if (cookie.getName().equals(cookieName)){
                return cookie.getValue();
            }
        }
        return null;
    }

    @Override
    public void getDeviceInfoAPi() {

    }

    @Override
    public String getRemoteAddr(HttpServletRequest request) {
        String addr = request.getRemoteAddr();
        return addr;
    }

    @Override
    public CityInfo getAddrInfoFromDB(String addr) {
        try {
            InputStream inputStream = FunctionServiceImpl.class.getClassLoader().getResourceAsStream("ipipfree.ipdb");
            City db = new City(inputStream);
            CityInfo cityInfo = db.findInfo(addr, "CN");
            System.out.println(cityInfo);
            return cityInfo;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IPFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String createAnOrderId() {
        DateFormat dateFormat01 = new SimpleDateFormat(dateFormat);
        return dateFormat01.format(new java.util.Date());
    }
}
