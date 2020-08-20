package com.lingtu.geoserver.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeoServerInfo {
    private String getEndpoint;
    private String userName;
    private String password;
}
