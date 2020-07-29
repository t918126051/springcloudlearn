package com.example.demo.pojo;



import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class LampPole implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long poleId;

    private String poleCode;

    private Long countyId;

    private Long roadId;

    private String gpsCode;

    private Long spcId;

    private String spcCode;

    private String kCode;

    private Long areaId;

    private Double gpsLon;

    private Double gpsLat;

    private Double amapLon;

    private Double amapLat;

    private Object lonlat;

    private String sPosition;

    private String poleType;

    private String lightType;

    private String lampPower;

    private String poleHeight;

    private Long mngId;

    private String lightInfo;

    private String applyName;

    private Date purchaseDt;

    private String nearPic;

    private String farPic;

    private String labelPic;

    private String labelSize;

    private String picCode;

    private String thmnPic;

    private Integer poleStatus;

    private Long officeId;

    private Long principalId;

    private Date createTime;

    private Date updateTime;

    private Date destroyTime;

    private String recycle;

    private String status;

    private String editLog;

    private String checkStatus;

    private String checkUser;

    private Date lastRepair;

    private Date lastReplace;

    private String remark;

}