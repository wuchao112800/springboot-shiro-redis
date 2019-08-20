package com.example.demo.Util;


import com.example.demo.Exception.BusinessException;

public class BSUtil {
    public static void isTrue(boolean expression, String error){
        if(!expression) {
            throw new BusinessException(error);
        }
    }
}
