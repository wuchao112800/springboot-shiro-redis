package com.example.demo.Controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {
    @RequiresPermissions("user:show")
    @ResponseBody
    @RequestMapping("/show")
    public String showUser(HttpServletRequest request) {
        System.out.println(request.getSession().getId());
        return "这是学生信息";
    }
}
