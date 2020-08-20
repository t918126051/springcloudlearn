package com.example.demo.mapper.impl;

import com.example.demo.mapper.MysqlTwoMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class MysqlTwoMapperImpl implements MysqlTwoMapper {
    @Autowired
    @Qualifier("mysql2JdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<Map<String, Object>> getAllSysUser() {
        return this.jdbcTemplate.queryForList("select  * from  sys_user ");
    }
}

