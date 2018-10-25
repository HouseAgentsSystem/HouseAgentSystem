package com.houseAgent.user.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.houseAgent.common.SMS.SMSCode;
import com.houseAgent.common.web.ExtAjaxResponse;
import com.houseAgent.common.web.ExtjsPageRequest;
import com.houseAgent.common.web.SessionUtil;
import com.houseAgent.staff.domain.StaffDTO;
import com.houseAgent.user.domain.User;
import com.houseAgent.user.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/sendCode")
	public void sendCode(HttpServletRequest req,HttpServletResponse resp) throws IOException
			{
			String phoneNumber = req.getParameter("phoneNumber");
			System.out.println("手机号码为："+phoneNumber);
			if(phoneNumber.trim().equals("")||phoneNumber == null) {
				System.out.println("手机号码为空");
				return;
			}
			String type;	//findPassword 忘记密码, register 注册
			if(checkPhoneNumberExist(req, resp)) {	//如果手机号码存在，是忘记密码的验证码
				//忘记密码验证码模板
				type = "findPassword";
			}else {
				//注册验证码模板
				type = "register";
			}
			StringBuffer buffer = new StringBuffer();
			Random random = new Random();
			for(int i=0;i<6;i++) {
				buffer.append(random.nextInt(10));
			}
			System.out.println(buffer.toString());
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter out = resp.getWriter();
			try {
				if(!SMSCode.sendCode(phoneNumber, buffer.toString(), type)) {
					out.println("验证码发送失败");
				}else {
					req.getSession().setAttribute("code",buffer.toString());
					req.getSession().setAttribute("number", phoneNumber);
					req.getSession().setAttribute("time", System.currentTimeMillis());
					out.println("验证码发送成功");
				}
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			out.close();
			}
	@RequestMapping("/registered")
	public ExtAjaxResponse registered(HttpServletRequest req,HttpServletResponse resp) {
		String phoneNumber = req.getParameter("phone");
		String code = req.getParameter("code");
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		String realname = req.getParameter("realname");
		HttpSession session = req.getSession();
		String code_Session = (String) session.getAttribute("code");
		String number = (String) session.getAttribute("number");
		Long time = (Long) session.getAttribute("time");
		session.removeAttribute("code");
		session.removeAttribute("number");
		session.removeAttribute("time");
		if((System.currentTimeMillis() - time) / 1000 /60 >=1) {
			System.out.println("验证码已失效，超过1分钟");
			return new ExtAjaxResponse(false,"验证码失效！！！");
		}
		if(code_Session.trim().equalsIgnoreCase(code)) {
			User  user = new User();
			user.setPhoneNumber(phoneNumber);
			user.setPassword(password);
			user.setStatus(1);
			user.setCreateTime(new Date());
			user.setUserName(userName);
			user.setFaceImage("default.jpg");
			user.setRealname(realname);
			if(userService.register(user)==null) {
				System.out.println("该账号名已被注册！");
				return new ExtAjaxResponse(false,"该账号名已被注册！");
			}
			System.out.println("注册成功");
			return new ExtAjaxResponse(true,"注册成功！");
		}else {
			System.out.println("验证码不正确");
			return new ExtAjaxResponse(false,"验证码错误！！！");
		}
	}
	@RequestMapping("/update")
	public ExtAjaxResponse updata(HttpServletRequest req,HttpServletResponse resp) {
		String phoneNumber = req.getParameter("phone2");
		String code = req.getParameter("code2");
		String password = req.getParameter("newPassword");
		HttpSession session = req.getSession();
		String code_Session = (String) session.getAttribute("code");
		String number = (String) session.getAttribute("number");
		Long time = (Long) session.getAttribute("time");
		session.removeAttribute("code");
		session.removeAttribute("number");
		session.removeAttribute("time");
		if((System.currentTimeMillis() - time) / 1000 /60 >=1) {
			System.out.println("验证码已失效，超过1分钟");
			return new ExtAjaxResponse(false,"验证码失效！！！");
		}
		if(code_Session.trim().equalsIgnoreCase(code)) {
			User  user = userService.findByPhoneNumber(phoneNumber);
			String salt = new SecureRandomNumberGenerator().nextBytes(32).toHex();
			user.setSalt(salt);
			String passwordR = new SimpleHash("MD5",password, user.getCredentialsSalt(), 2).toString();
			user.setPassword(passwordR);
			userService.addUser(user);
			System.out.println("修改密码成功");
			return new ExtAjaxResponse(true,"修改密码成功！");
		}else {
			System.out.println("验证码不正确");
			return new ExtAjaxResponse(false,"验证码错误！！！");
		}
	}
	@RequestMapping("/login")
	public ExtAjaxResponse login(HttpSession session, HttpServletRequest req,HttpServletResponse resp) {
		String userName = req.getParameter("user");
		String password = req.getParameter("password");
		System.out.println(password);
		User user = userService.login(userName, password);
		if(user!= null) {
			user.setPassword("");
			SessionUtil.setUser(session, user);
			System.out.println("登录成功");
			return new ExtAjaxResponse(true,"登录成功！");
		}else {
			System.out.println("登录失败");
			return new ExtAjaxResponse(false,"登录失败！！！");
		}
	}
	
	@RequestMapping("/logout")
	public ExtAjaxResponse logout(HttpSession session) {
		session.invalidate();
		System.out.println(session);
		System.out.println("登出成功");
		return new ExtAjaxResponse(true,"登出成功！");
	}
	
	/**
	 * 检测账号是否可用
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("/checkUserName")
	public Boolean checkUserName(HttpServletRequest req,HttpServletResponse resp) {
		String userName = req.getParameter("userName");
		if(userService.checkUserName(userName)) {
			System.out.println("该账号名可用");
			return true;
		}
		System.out.println("该账号已被注册！");
		return false;
	}
	/**
	 * 检测手机号码是否可用
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("/checkPhoneNumber")
	public Boolean checkPhoneNumber(HttpServletRequest req,HttpServletResponse resp) {
		String phoneNumber = req.getParameter("phoneNumber");
		if(userService.checkPhoneNumber(phoneNumber)) {
			System.out.println("该手机号码可用");
			return true;
		}
		System.out.println("该手机号码已被绑定！");
		return false;
	}
	
	/**
	 * 检测手机号码是否存在，用于忘记密码时
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("/checkPhoneNumberExist")
	public Boolean checkPhoneNumberExist(HttpServletRequest req,HttpServletResponse resp) {
		String phoneNumber = req.getParameter("phoneNumber");
		System.out.println(phoneNumber);
		if(!userService.checkPhoneNumber(phoneNumber)) {
			System.out.println("该手机号码存在");
			return true;
		}
		System.out.println("该手机号码不存在！");
		return false;
	}
	@RequestMapping("/updataInformation")
	public ExtAjaxResponse updataInformation(HttpServletRequest request, HttpSession session) {
		
		Long userId = SessionUtil.getUserId(session);
		System.out.println(userId);
		if(userId!=null) {
			User user2 = userService.findOne(userId);
			user2.setFaceImage(request.getParameter("faceImage"));
			user2.setRealname(request.getParameter("realname"));
			userService.updataUser(user2);
			return new ExtAjaxResponse(true,"修改成功！");
		}else {
			return new ExtAjaxResponse(false,"请登录！");
		}
	}
	
	@RequestMapping("/imagesUpload")
    public String imagesUpload(@RequestParam(value = "fileList", required = true) MultipartFile[] files,
            HttpServletRequest request, HttpSession session) {
		Long userId = SessionUtil.getUserId(session);
		System.out.println(userId);
		if(userId!=null) {
			List<String> list = new ArrayList<String>();
	        System.out.println(files.length);
	        if (files != null && files.length > 0) {
	            for (int i = 0; i < files.length; i++) {
	                MultipartFile file = files[i];
	                list = saveFile(request, file, list);
	            }
	        }
	        for (int i = 0; i < list.size(); i++) {
	            System.out.println("集合里面的数据" + list.get(i));
	            return list.get(i);
	        }
		}
		return null;
        
    }
	private List<String> saveFile(HttpServletRequest request,
            MultipartFile file, List<String> list) {
        if (!file.isEmpty()) {
            try {
                String filePath = request.getSession().getServletContext()
                        .getRealPath("/")
                        + "Customer/upload/user/" + file.getOriginalFilename();
                System.out.println(filePath);
                list.add(file.getOriginalFilename());
                File saveDir = new File(filePath);
                if (!saveDir.getParentFile().exists())
                    saveDir.getParentFile().mkdirs();
                file.transferTo(saveDir);
                return list;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
