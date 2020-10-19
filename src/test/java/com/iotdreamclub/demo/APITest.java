package com.iotdreamclub.demo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class APITest {
    @Test
    public void testPostData() {
        // int
        int pInt = 0;
        // String
        String pString = "String";
        // String []
        String [] pStrings = {"String [0]", "String [1]"};
        // List
        List<String> pLists = List.of("list[0]", "list[1]");
        // 。。

        Map<String, Object> params = new HashMap<>();
        params.put("p-int", pInt);
        params.put("p-string", pString);
        params.put("p-strings", pStrings);
        params.put("p-list", pLists);

        String url = "http://localhost:8080/api/get-info";
    }
}
