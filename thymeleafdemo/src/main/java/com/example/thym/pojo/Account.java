package com.example.thym.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private String account;
    private String name;
    private String password;
    private String accountType;
    private String tel;
}
