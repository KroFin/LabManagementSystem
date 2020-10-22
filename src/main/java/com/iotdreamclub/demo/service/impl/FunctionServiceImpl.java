package com.iotdreamclub.demo.service.impl;

import com.iotdreamclub.demo.service.FunctionService;
import net.ipip.ipdb.City;
import net.ipip.ipdb.IPFormatException;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

@Service
public class FunctionServiceImpl implements FunctionService {
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
    public void getAddrInfoFromDB(String addr , String language) {

        try {
            City db = new City("E:\\大三Java\\LabManagementSystem\\src\\main\\java\\com\\iotdreamclub\\demo\\db\\ipipfree.ipdb");
            //db.find(addr,language);
            System.out.println(Arrays.toString(db.find("1.1.1.1","CN")));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IPFormatException e) {
            e.printStackTrace();
        }

    }
}
