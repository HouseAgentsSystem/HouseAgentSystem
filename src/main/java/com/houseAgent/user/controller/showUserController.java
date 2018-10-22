package com.houseAgent.user.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.houseAgent.house.domain.HouseDTO;
import com.houseAgent.user.domain.User;
import com.houseAgent.user.service.UserService;

@Controller
@RequestMapping("/showUser")
public class showUserController {
	@Autowired
	private UserService userService;
	@GetMapping("/information")
	public String showUser(Long id,Map<String,Object> model) {
		System.out.println(id);
		User user = userService.findOne(id);
		System.out.println(user);
		model.put("userInformation",user);
		return "information";
	}
}
