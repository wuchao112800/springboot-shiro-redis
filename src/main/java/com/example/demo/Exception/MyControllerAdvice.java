package com.example.demo.Exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class MyControllerAdvice {

    @ExceptionHandler(value = UnauthorizedException.class)
    public String aa(){
        System.out.println("-----MyControllerAdvice------");
        //跳转页面
        return "您没有权限！";
    }

    /**
     * 处理所有业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    String handleBusinessException(BusinessException e){
        return e.getMessage();
    }
}

