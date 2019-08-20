package com.example.demo.Realm;

import com.example.demo.Model.User;
import com.example.demo.Service.UserServiceDetail;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomRealm extends AuthorizingRealm {

    @Autowired
    UserServiceDetail userServiceDetail;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
       String temp = (String) principals.getPrimaryPrincipal();
       User user = userServiceDetail.findByUsername(temp);
       SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
       user.getRoles().forEach(role -> {
           authorizationInfo.addRole(role.getName());
           role.getAuthorities().forEach(authority -> {
               authorizationInfo.addStringPermission(authority.getFunction_name());
           });
       });
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("-------身份认证方法--------");
        String userName = (String) authenticationToken.getPrincipal();
        String userPwd = new String((char[]) authenticationToken.getCredentials());

        //根据用户名从数据库获取密码
        User user = userServiceDetail.findByUsernameAndPassword(userName,userPwd);

        if (user == null){
            throw new AccountException("用户名或密码不正确");
        }

        return new SimpleAuthenticationInfo(userName, userPwd,getName());
    }
}
