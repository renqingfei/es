package com.example.demo_es.shiroconf;

import com.example.demo_es.dao.AddressMapper;
import com.example.demo_es.entity.Address;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class MyRealm extends AuthorizingRealm {
    @Autowired
    private AddressMapper mapper;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //得到身份信息
        String name = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //根据名字查询对应的角色
        //select role from role where user_id =(select id from user where name=#{name});
        //info.addRole(role);
        //根据角色查询对应的权限
        //select quanxian from quanxian where role=#{role};
        //info.addStringPermissions(quanxian);
        return info;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String  name = (String) token.getPrincipal();
        Address address = mapper.selectName(name);
        if(address!=null){
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(address.getName(),address.getPhone(), ByteSource.Util.bytes("ASDF"),this.getName());
            return info;
        }
        return null;
    }
}
