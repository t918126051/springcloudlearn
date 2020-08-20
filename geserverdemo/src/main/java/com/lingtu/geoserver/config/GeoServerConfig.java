//package com.lingtu.geoserver.config;
//
//import com.lingtu.geoserver.pojo.GeoServerInfo;
//import it.geosolutions.geoserver.rest.GeoServerRESTManager;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.net.URL;
//
//@Configuration
//
//public class GeoServerConfig {
//
//    @Autowired
//    private GeoServerProperties geoServerProperties;
//
//    @Bean(name = "geoServerInfoFactory")
//
//    public GeoServerInfo geoServerInfoFactory() {
//        return new GeoServerInfo(geoServerProperties.getEndpoint(), geoServerProperties.getUsername(),
//                geoServerProperties.getPassword());
//    }
//
//    @Bean(name = "geoServerRESTManagerFactory")
//    public GeoServerRESTManager geoServerRESTManagerFactory() {
//        try {
//            return new GeoServerRESTManager(new URL(geoServerProperties.getEndpoint()),
//                    geoServerProperties.getUsername(), geoServerProperties.getPassword());
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return null;
//        }
//    }
//}