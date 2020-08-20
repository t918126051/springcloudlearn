package com.example.demo.pojo.mysql2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUser {
    private Integer userId;
    private String userName;
    private String password;
}
