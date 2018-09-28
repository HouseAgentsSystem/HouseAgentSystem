package com.houseAgent.house.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.houseAgent.house.domain.House;
import com.houseAgent.house.service.HouseService;


@Controller
@RequestMapping("/showHouse")
public class showHouseController {
	@Autowired
	private HouseService houseService;
	@GetMapping("/details")
	public String showHouse(Long id,Map<String,Object> model) {
		System.out.println(id);
		House house = houseService.findOne(id).get();
		System.out.println(house);
		model.put("house",house);
		return "details";
	}
}
