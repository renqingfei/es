package com.example.demo_es.shiroconf;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConf {
    /*@Bean
    public ShiroFilterFactoryBean getshirofilter(SecurityManager securityManager){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        Map<String, String> map = new HashMap<>();
        map.put("/login/login","anon");
        map.put("/login/**","anon");
        map.put("/**","authc");
        factoryBean.setLoginUrl("/login/login.jsp");
        factoryBean.setFilterChainDefinitionMap(map);
        return factoryBean;
    }*/

}
