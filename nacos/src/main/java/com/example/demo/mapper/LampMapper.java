package com.example.demo.mapper;

import com.example.demo.bo.LampConditionAndNumBo;
import com.example.demo.pojo.LampPole;
import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface LampMapper {
  List<LampConditionAndNumBo> selectCountyNumByLamp();
  int createTable(@Param("tableName") String tableName);
  int existTable(@Param("tableName") String tableName);
  LampPole selectPoleById( Long poleId);
  int insertUser(User user);
  User getUser(String userName,String password);
  User getUserIsExist(String userName);
}
