package com.spring.controller;


import com.spring.annotation.CustomController;
import com.spring.annotation.CustomQualifier;
import com.spring.annotation.CustomRequestMapping;
import com.spring.annotation.CustomRequestParam;
import com.spring.service.MyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@CustomController
@CustomRequestMapping("/custom")
public class MyController {

    @CustomQualifier("MyServiceImpl")
    private MyService myService;

    @CustomRequestMapping("/query")
    public void query(HttpServletRequest request, HttpServletResponse response,
                      @CustomRequestParam("name") String name, @CustomRequestParam("age") Integer age) {
        PrintWriter pw;
        try {
            PrintWriter writer = response.getWriter();
            String result = myService.query(String.valueOf(name), String.valueOf(age));
            writer.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
