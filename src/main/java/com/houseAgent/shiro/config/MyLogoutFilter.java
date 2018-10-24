package com.houseAgent.shiro.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.springframework.stereotype.Service;


/**
 * Servlet Filter implementation class MyLogoutFilter
 */

public class MyLogoutFilter extends LogoutFilter{
    
	
//	@Override
//    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
//        Subject subject = getSubject(request, response);
//        String redirectUrl = getRedirectUrl(request, response, subject);
//        //try/catch added for SHIRO-298:
//        try {
//            subject.logout();
//        } catch (SessionException ise) {
//            log.debug("Encountered session exception during logout.  This can generally safely be ignored.", ise);
//        }
//        issueRedirect(request, response, redirectUrl);
//        return false;
//    }
	
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        System.out.println("This is MyLogoutFilter()");
	    Subject subject = getSubject(request, response);
	    String redirectUrl = getRedirectUrl(request, response, subject);
	    //try/catch added for SHIRO-298:
	    
	    try {
	        subject.logout();
	        if (!"XMLHttpRequest".equalsIgnoreCase(httpServletRequest.getHeader("X-Requested-With"))){// 不是ajax请求
		    	issueRedirect(request, response, redirectUrl);
	    	} else {
	    		httpServletResponse.setCharacterEncoding("UTF-8");
	    		PrintWriter out = httpServletResponse.getWriter();
	    		System.out.println("{success:true,message:'登出成功'}");
	    		out.println("{success:true,message:'登出成功'}");
	    		out.flush();
	    		out.close();
	    	}
	    } catch (SessionException ise) {
	    	ise.printStackTrace();
	    }
	    return false;
	}
}
