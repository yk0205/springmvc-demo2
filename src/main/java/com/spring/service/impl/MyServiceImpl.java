package com.spring.service.impl;


import com.spring.annotation.CustomService;
import com.spring.service.MyService;

@CustomService("MyServiceImpl")
public class MyServiceImpl implements MyService {

    @Override
    public String query(String name, String age) {
        return "name:" + name + ";age:" + age;
    }
}
