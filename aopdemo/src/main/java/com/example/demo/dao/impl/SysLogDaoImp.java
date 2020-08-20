package com.example.demo.dao.impl;

import com.example.demo.dao.SysLogDao;
import com.example.demo.pojo.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class SysLogDaoImp implements SysLogDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void saveSysLog(SysLog syslog) {
		System.out.println(syslog.toString());
		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(this.jdbcTemplate.getDataSource());
		String sql = "insert into sys_log (username,operation,time,method,params,ip,create_time) values (:username,:operation,:time,:method,:params,:ip,:createTime)";
		SqlParameterSource ps=new BeanPropertySqlParameterSource(syslog);

		KeyHolder keyholder=new GeneratedKeyHolder();

		npjt.update(sql,ps,keyholder);
	}

}