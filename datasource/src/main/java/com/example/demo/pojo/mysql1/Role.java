package com.example.demo.pojo.mysql1;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Role implements Serializable {
    private int id;
    private String roleName;
}
