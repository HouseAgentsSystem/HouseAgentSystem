package com.houseAgent.house.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.houseAgent.house.domain.House;
import com.houseAgent.house.domain.HouseDTO;
import com.houseAgent.house.service.HouseService;


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
	public String addHouse(House house) {
		System.out.println(house);
		house.setState(1);
		house.setAgencyFees((double) 5000);
		houseService.addOneHouse(house);
		return null;
	}
	
}
