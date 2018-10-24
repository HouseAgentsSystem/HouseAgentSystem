package com.houseAgent.house.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.houseAgent.common.web.SessionUtil;
import com.houseAgent.house.domain.House;
import com.houseAgent.house.domain.HouseDTO;
import com.houseAgent.house.service.HouseService;
import com.houseAgent.staff.domain.Staff;


@Controller
@RequestMapping("/showHouse")
public class showHouseController {
	@Autowired
	private HouseService houseService;
	@GetMapping("/details")
	public String showHouse(Long id,Map<String,Object> model) {
		System.out.println(id);
		HouseDTO housedto = houseService.findOne(id);
		System.out.println(housedto);
		model.put("house",housedto);
		return "details";
	}
	@GetMapping("/addHouse")
	public String addHouse(House house,HttpSession session) {
		System.out.println(house);
		house.setState(1);
		Staff staff = SessionUtil.getStaff(session);
		System.out.println(staff);
		house.setStaff(staff);
		System.out.println(staff.getStore());
		house.setStore(staff.getStore());
		houseService.addOneHouse(house);
		return "addsucceed";
	}
	
}
