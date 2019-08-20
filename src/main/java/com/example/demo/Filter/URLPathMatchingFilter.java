package com.example.demo.Filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class URLPathMatchingFilter extends PathMatchingFilter {
    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        System.out.println("-------URLPathMatchingFilter--------------");
        String url = getPathWithinApplication(request);
        System.out.println("url:"+url);
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()){
            // 如果没有登录, 直接返回true 进入登录流程
            System.out.println("如果没有登录, 直接返回true 进入登录流程");
            return  true;
        }
        url = url.substring(1,url.length()).replace("/",":");
        System.out.println("新的 url："+url);
        if (!subject.isPermitted(url)) {
            WebUtils.issueRedirect(request, response, "/unauthorized"); //重定向
            return  false;
        }
        return  true;
    }

}
