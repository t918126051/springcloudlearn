package com.example.demo.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Primary
    @Bean(name = "mysql1datasource")
    @ConfigurationProperties("spring.datasource.druid.mysql1")
    public DataSource dataSourceOne(){
        return DruidDataSourceBuilder.create().build();
    }
    @Bean(name = "mysql2datasource")
    @ConfigurationProperties("spring.datasource.druid.mysql2")
    public DataSource dataSourceTwo(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "mysql1JdbcTemplate")
    public JdbcTemplate primaryJdbcTemplate(
            @Qualifier("mysql1datasource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "mysql2JdbcTemplate")
    public JdbcTemplate secondaryJdbcTemplate(@Qualifier("mysql2datasource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
