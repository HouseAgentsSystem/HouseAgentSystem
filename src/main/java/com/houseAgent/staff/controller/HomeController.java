package com.houseAgent.staff.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.activiti.engine.IdentityService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.houseAgent.common.web.ExtAjaxResponse;
import com.houseAgent.common.web.SessionUtil;
import com.houseAgent.shiro.config.MyShiroRealm;
import com.houseAgent.staff.domain.Staff;
import com.houseAgent.staff.service.IGroupService;


@RestController
public class HomeController {
	private static Logger logger = LoggerFactory.getLogger(HomeController.class);
    @Autowired
    private IdentityService identityService;
    @Autowired
    private MyShiroRealm myShiroRealm;
    @Autowired
    private IGroupService groupService;
	@RequestMapping({"/","/index"})
	public String index(){
		return "/index";
	}
	
//	@RequestMapping(value="/login",method=RequestMethod.GET)
//	public String login(){
//		return "login";
//	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public @ResponseBody ExtAjaxResponse login(@RequestParam("userName") String userName, @RequestParam("password") String password, HttpSession session) {
		logger.debug("logon request: {userName={}, password={}}", userName, password);
		System.out.println("HomeController.login()");
		try {
			// shiro 登录
			UsernamePasswordToken token = new UsernamePasswordToken(userName,password);
			Subject subject = SecurityUtils.getSubject();
			subject.login(token);
			AuthorizationInfo authorizationInfo = myShiroRealm.doGetAuthorizationInfo(subject.getPrincipals());
			// 查看用户是否存在
			System.out.println(authorizationInfo.getRoles());//角色
			System.out.println(subject.getPrincipal());//用户信息Staff
			Staff staff = (Staff)subject.getPrincipal();
			SessionUtil.setStaff(session, staff);
			//------------------
			Collection<String> groups = authorizationInfo.getRoles();
			String[] groupStrings = new String[groups.size()];
			groups.toArray(groupStrings);
	        System.out.println(groupStrings);
//	        //读取角色Group
	        String groupId = String.join(",", groupStrings);
	        String groupName = groupService.findById(groupId).getName();
	        SessionUtil.setGroupNames(session, groupName);//"groupNames"  : "admin,hrManager"
	        String faceImg = staff.getFaceImg();
	        System.out.println(SessionUtil.getGroupNames(session));
	        Map<String,String> map=new HashMap<String, String>();
	        map.put("realName", staff.getRealName());
	        map.put("msg", "登录成功!");
	        map.put("role", SessionUtil.getGroupNames(session));
	        map.put("faceImg", faceImg);
	        //map.put("loginUserImage", staff.getImgUrl());
	        
	        return new ExtAjaxResponse(true,map);
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ExtAjaxResponse(false,"登录失败!帐号或者密码有误!请重新登录!");
	}
	/**
   * 退出登录
   */
	@RequestMapping(value = "/logout")
	public @ResponseBody ExtAjaxResponse logout(HttpSession session) {
		System.out.println("logoutController!!!!");
		try {
			SessionUtil.removeAttribute(session);
			SecurityUtils.getSubject().logout();
			return new ExtAjaxResponse(true,"登出成功!");
		} catch (Exception e) {
			return new ExtAjaxResponse(false,"登出失败!");
		}
	}
	/**
	 * 获取当前员工信息
	 * @param session
	 * @return 员工图片，员工姓名，员工角色
	 */
	@RequestMapping(value = "/getInformation")
	public @ResponseBody ExtAjaxResponse getInformation(HttpSession session) {
		
		try {
			System.out.println("getInformationController!!!!");
			Subject subject = SecurityUtils.getSubject();
			Staff staff = (Staff)subject.getPrincipal();
			System.out.println(staff);
			String realName = staff.getRealName();
			String faceImg = staff.getFaceImg();
			String roleName = SessionUtil.getGroupNames(session);//角色
			System.out.println(SessionUtil.getGroupNames(session));
			
			Map<String,String> map=new HashMap<String, String>();
	        map.put("realName", realName);
	        map.put("msg", "获取信息成功!");
	        map.put("role", roleName);
	        map.put("faceImg", faceImg);
			return new ExtAjaxResponse(true,map);
		} catch (Exception e) {
			return new ExtAjaxResponse(false,"获取信息失败!");
		}
	}
}
