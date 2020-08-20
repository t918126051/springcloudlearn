package com.example.demo.service;

import com.alibaba.nacos.api.naming.pojo.AbstractHealthChecker;
import com.example.demo.dto.NanChangResult;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {
    NanChangResult loginIn(String userName, String password, HttpServletRequest request);
}
