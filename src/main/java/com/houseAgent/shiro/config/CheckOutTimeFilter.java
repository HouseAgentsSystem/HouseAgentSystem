package com.houseAgent.shiro.config;

import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.AdviceFilter;

import com.houseAgent.common.web.SessionUtil;


public class CheckOutTimeFilter extends AdviceFilter {

	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("CheckOutTimeFilter:preHandle");
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String userId = SessionUtil.getStaffId(httpServletRequest.getSession());
        if (null == userId && !StringUtils.contains(httpServletRequest.getRequestURI(), "/login")) {
            String requestedWith = httpServletRequest.getHeader("X-Requested-With");
            if (StringUtils.isNotEmpty(requestedWith) && StringUtils.equals(requestedWith, "XMLHttpRequest")) {//如果是ajax返回指定数据
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.setContentType("application/json");
                PrintWriter out = httpServletResponse.getWriter();
                out.write("{success:false,message:'身份认证过期，请重新登录！'}");
                out.flush();
                out.close();
                return false;
            } else {//不是ajax进行重定向处理
                httpServletResponse.sendRedirect("/Admin/index.html#login");
                return false;
            }
        }
        return true;
	}

	
}
