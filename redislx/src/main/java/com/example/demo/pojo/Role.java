package com.example.demo.pojo;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Role implements Serializable {
    private int id;
    private String roleName;
}
