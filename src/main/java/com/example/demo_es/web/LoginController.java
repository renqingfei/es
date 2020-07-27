package com.example.demo_es.web;

import com.example.demo_es.common.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Api("LoginController|登录")
public class LoginController {
    @RequestMapping("/login")
    @ApiOperation(value = "登录",notes = "登录")
    public void login(String name, String password){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(name, password);
        try {
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException e) {
             throw new RuntimeException("用户名错误");
        } catch (IncorrectCredentialsException e) {
            throw new RuntimeException("用户名错误");
        }
    }
    @RequestMapping("/exit")
    @ApiOperation(value = "安全退出登录",notes = "安全退出登录")
    public void exit(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }
}
