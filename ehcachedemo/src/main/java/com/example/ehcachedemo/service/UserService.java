package com.example.ehcachedemo.service;

import com.example.ehcachedemo.pojo.User;

import java.util.List;

public interface UserService {

    List<User> list();
    User get(Integer id);
    User save(User user);
    User update(User user);
    void delete(Integer id);

}