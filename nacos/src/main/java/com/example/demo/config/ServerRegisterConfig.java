package com.example.demo.config;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.api.naming.pojo.Service;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class ServerRegisterConfig {

//    @NacosInjected
//    private NamingService namingService;
//
//    @PostConstruct
//    public void registerInstance() throws NacosException {
//        String serviceName = "server";
//        String groupName = "web";
//        Service service = new Service(serviceName);
//        Instance instance = new Instance();
//        instance.setIp("127.0.0.1");
//        instance.setPort(8990);
//        instance.setHealthy(true);
//        instance.setWeight(1.0);
//        instance.setServiceName(serviceName);
//        instance.setClusterName(groupName);
//        namingService.registerInstance( "web",instance);
//    }

}
