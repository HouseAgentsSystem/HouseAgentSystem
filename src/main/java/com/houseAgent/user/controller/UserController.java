package com.houseAgent.user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.houseAgent.common.SMS.SMSCode;
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
				resp.sendRedirect("index.jsp");
				return;
			}
			StringBuffer buffer = new StringBuffer();
			Random random = new Random();
			for(int i=0;i<6;i++) {
				buffer.append(random.nextInt(10));
			}
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter out = resp.getWriter();
			try {
				if(!SMSCode.sendCode(phoneNumber,buffer.toString())) {
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
	public void registered(HttpServletRequest req,HttpServletResponse resp) {
		String phoneNumber = req.getParameter("phoneNumber");
		String code = req.getParameter("code");
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		HttpSession session = req.getSession();
		String code_Session = (String) session.getAttribute("code");
		String number = (String) session.getAttribute("number");
		Long time = (Long) session.getAttribute("time");
		session.removeAttribute("code");
		session.removeAttribute("number");
		session.removeAttribute("time");
		if((System.currentTimeMillis() - time) / 1000 /60 >=2) {
			System.out.println("验证码已失效，超过2分钟");
			return;
		}
		if(code_Session.trim().equalsIgnoreCase(code)) {
//			Customer customer = new Customer();
//			customer.setPhoneNumber(phoneNumber);
//			customer.setUserName(userName);
//			customer.setPassword(password);
//			customer.setCreateTime(new Date());
//			customerService.registered(customer);
			System.out.println("注册成功");
		}else {
			System.out.println("验证码不正确");
			return;
		}
	}
}
