package com.lingtu.geoserver.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "spring.datagaea.geoserver")
public class GeoServerProperties {

    private String endpoint;

    private String username;

    private String password;

    private String workspacename;
}