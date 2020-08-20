package com.lingtu.geoserver;

import com.lingtu.geoserver.config.GeoServerProperties;
import com.lingtu.geoserver.util.PublishShpUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestDemo {
    @Autowired
    private PublishShpUtil publishShpUtil;
    @Autowired
    private GeoServerProperties geoServerProperties;
    @Test
    public void test() throws Exception {
        String url = geoServerProperties.getEndpoint();
        String username = geoServerProperties.getUsername();
        String password = geoServerProperties.getPassword();
     publishShpUtil.GeoserverPublishShapefileData(url,username,password);
    }
}
