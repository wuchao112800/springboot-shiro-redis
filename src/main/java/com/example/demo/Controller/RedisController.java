package com.example.demo.Controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/admin")
public class RedisController {

    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        String user = (String) request.getSession().getAttribute("user");
        System.out.println(user);
        return "please login first";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, String username, String password) {

        return "login success";
    }

    @RequestMapping("/logout")
    public String login(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "安全退出！";
    }
}
