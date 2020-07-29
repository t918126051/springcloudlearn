package com.example.demo.bo;

import lombok.Data;

import java.io.Serializable;

@Data
public class LampConditionAndNumBo implements Serializable {
    private String conditionName;
    private Integer lampCount;
}