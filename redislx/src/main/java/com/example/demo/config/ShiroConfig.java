package com.example.demo.config;


import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Bean("userRealm")
    public UserRealm userRealm(){
        return new UserRealm();
    }

    @Bean
    public DefaultWebSecurityManager defaultSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return  securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String,String> filterMap = new LinkedHashMap<>();
     /*
      * anon：无需认证就可以访问
      * authc：必须认证了才能访问
      * user：必须用有了 记住我 功能才能用
      * perms：拥有对某个资源的权限才能访问
      * role：拥有某个角色权限才能访问
      */
       filterMap.put("/user/add","authc");
       filterMap.put("/user/update","authc");
       shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
     //shiroFilterFactoryBean.setLoginUrl("/login");
        return shiroFilterFactoryBean;
 }
}
