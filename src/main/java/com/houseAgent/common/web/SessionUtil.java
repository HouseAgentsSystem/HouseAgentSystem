package com.houseAgent.common.web;

import javax.servlet.http.HttpSession;

import com.houseAgent.staff.domain.Staff;
import com.houseAgent.user.domain.User;


public class SessionUtil {
	public static final String USER 		= "user";		//普通用户
	public static final String USERNAME 	= "userName";
	public static final String USERId 		= "userId";
	public static final String STAFF 		= "staff";		//员工用户
	public static final String STAFFId 		= "staffId";
//	public static final String GROUPS 		= "groups";s
	public static final String GROUPNAMES 	= "groupNames";
    /**
     * 设置普通用户到session
     */
    public static void setUser(HttpSession session, User user) {
        session.setAttribute(USER, user);
        setUserName(session,user.getUserName());
        setUserId(session,user.getId());
    }

    /**
     * 从Session获取当前普通用户信息
     */
    public static User getUser(HttpSession session) {
        Object user = session.getAttribute(USER);
        return user == null ? null : (User) user;
    }

    /**
     * 设置普通用户名到session
     */
    public static void setUserName(HttpSession session, String userName) {
        session.setAttribute(USERNAME, userName);
    }

    /**
     * 从Session获取当前普通用户名
     */
    public static String getUserName(HttpSession session) {
        Object userName = session.getAttribute(USERNAME);
        return userName == null ? null : (String) userName;
    }
    
    /**
     * 设置普通用户名到session
     */
    public static void setUserId(HttpSession session, Long userId) {
        session.setAttribute(USERId, userId);
    }

    /**
     * 从Session获取当前普通用户名
     */
    public static Long getUserId(HttpSession session) {
        Object userId = session.getAttribute(USERId);
        return userId == null ? null : (Long) userId;
    }
    
    /**
     * 设置员工用户到session
     */
    public static void setStaff(HttpSession session, Staff staff) {
        session.setAttribute(STAFF, staff);
        setStaffId(session,staff.getId());
    }
    
    /**
     * 从Session获取当前员工用户信息
     */
    public static Staff getStaff(HttpSession session) {
        Object staff = session.getAttribute(STAFF);
        return staff == null ? null : (Staff) staff;
    }

    /**
     * 设置员工用户名到session
     */
//    public static void setStaffName(HttpSession session, String staffName) {
//        session.setAttribute(STAFFNAME, staffName);
//    }
    public static void setStaffId(HttpSession session, Long staffId) {
        session.setAttribute(STAFFId, staffId);
    }
    /**
     * 从Session获取当前员工用户名
     */
    public static Long getStaffId(HttpSession session) {
        Object staffId = session.getAttribute(STAFFId);
        return staffId == null ? null : (Long) staffId;
    }

    /**
     * 设置groups到session
     */
    public static void setGroupNames(HttpSession session, String groupNames) {
        session.setAttribute(GROUPNAMES, groupNames);
    }

    /**
     * 从Session获取当前groups信息
     */
	public static String getGroupNames(HttpSession session) {
        Object groupNames = session.getAttribute(GROUPNAMES);
        return groupNames == null ? null : (String) groupNames;
    }
	
	public static void removeAttribute(HttpSession session) {
		
		session.removeAttribute(STAFF);
		session.removeAttribute(USERNAME);
//        session.removeAttribute(GROUPS);
        session.removeAttribute(STAFFId);
        
        session.removeAttribute(USER);
		session.removeAttribute(USERNAME);
    }
}

