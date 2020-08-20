package com.example.demo.mapper;

import com.example.demo.pojo.Permissions;
import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User queryUserByUsername(@Param("username") String username);

    Permissions queryPermissionByUsername(@Param("username") String username);

    User selectUserByNameAndPwd(String userName, String password);

    User selectUserByUserName(String userName);

}