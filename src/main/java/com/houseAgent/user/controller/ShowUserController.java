package com.houseAgent.user.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.houseAgent.common.web.ExtAjaxResponse;
import com.houseAgent.common.web.SessionUtil;
import com.houseAgent.house.domain.HouseDTO;
import com.houseAgent.user.domain.User;
import com.houseAgent.user.service.UserService;

@Controller
@RequestMapping("/showUser")
public class ShowUserController {
	@Autowired
	private UserService userService;
	/**
	 * 跳转到用户个人管理
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/information")
	public String information(Map<String,Object> model, HttpSession session) {
		Long userId = SessionUtil.getUserId(session);
		System.out.println(userId);
		if(userId!=null) {
			User user = userService.findOne(userId);
			System.out.println(user);
			model.put("userInformation",user);
			return "information";
		}else {
			return "index";
		}
	}
	@GetMapping("/addHouseRent")
	public String addHouseRent(Map<String,Object> model, HttpSession session) {
		Long userId = SessionUtil.getUserId(session);
		System.out.println(userId);
		if(userId!=null) {
			User user = userService.findOne(userId);
			System.out.println(user);
			model.put("userInformation",user);
			return "addHouseRent";
		}else {
			return "index";
		}
	}
	@GetMapping("/houseRentGrid")
	public String houseRentGrid(Map<String,Object> model, HttpSession session) {
		Long userId = SessionUtil.getUserId(session);
		System.out.println(userId);
		if(userId!=null) {
			User user = userService.findOne(userId);
			System.out.println(user);
			model.put("userInformation",user);
			return "houseRentGrid";
		}else {
			return "index";
		}
	}
	
}
