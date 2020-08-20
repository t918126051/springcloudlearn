package com.example.demo.config;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.Permissions;
import com.example.demo.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.annotation.Resource;

public class UserRealm extends AuthorizingRealm {
    @Resource
    private UserMapper userMapper;
    private static Logger logger = LoggerFactory.getLogger(UserRealm.class);
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("-----执行授权操作---------");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //拿到当前登录的对象
        Subject subject = SecurityUtils.getSubject();
        //获取到User
        User currentUser = (User) subject.getPrincipal();
        //设置当前用户的权限
        Permissions permissions = userMapper.queryPermissionByUsername(currentUser.getUsername());
        info.addStringPermission(permissions.getPermissionName());
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("-----执行认证操作-----------");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String userName = token.getUsername();
        User user = userMapper.selectUserByUserName(userName);
        if (user !=null){
            return new SimpleAuthenticationInfo("",user.getPassword(),"");
        }else{
            return null;
        }


    }
}
