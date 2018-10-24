package com.houseAgent.shiro.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;

/*
 * 扩展PermissionsAuthorizationFilter，用于ajax访问接口登录但是资源操作认证不通过的处理
 */
public class ShiroRolesFilter extends RolesAuthorizationFilter {

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
		System.out.println("ShiroRolesFilter:onAccessDenied");
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String requestedWith = httpServletRequest.getHeader("X-Requested-With");
        if (StringUtils.isNotEmpty(requestedWith) &&
                StringUtils.equals(requestedWith, "XMLHttpRequest")) {//如果是ajax返回指定格式数据
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType("application/json");
            PrintWriter out = httpServletResponse.getWriter();
            out.write("{success:false,message:'无权限！'}");
            out.flush();
            out.close();
        } else {//如果是普通请求进行重定向
            httpServletResponse.sendRedirect("/403");
        }
        return false;
	}

	@Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws IOException {
		Subject subject = getSubject(request, response);
        String[] rolesArray = (String[]) mappedValue;
//        List<String> roleIdentifiers = Arrays.asList(rolesArray);
//        boolean[] hasRoles = subject.hasRoles(roleIdentifiers);
//        for (int i = 0; i < hasRoles.length; i++) {
//			if(hasRoles[i]) {
//				return true;
//			}
//		}
        if (rolesArray == null || rolesArray.length == 0)
        {
            return true;
        }
        for(int i=0;i<rolesArray.length;i++)
        {
            if(subject.hasRole(rolesArray[i]))
            {
                return true;  
            }
        }

        return false;
	}
	
}
