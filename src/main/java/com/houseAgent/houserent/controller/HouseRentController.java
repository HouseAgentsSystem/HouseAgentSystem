package com.houseAgent.houserent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.houseAgent.common.web.ExtjsPageRequest;
import com.houseAgent.house.domain.HouseQueryDTO;
import com.houseAgent.houserent.domain.HouseRentBaseDTO;
import com.houseAgent.houserent.service.HouseRentService;

@RestController
@RequestMapping("/houseRent")
public class HouseRentController {
	@Autowired
	private HouseRentService houseRentService;
	@GetMapping
	public Page<HouseRentBaseDTO> getPage(ExtjsPageRequest pageRequest) 
	{
		return houseRentService.findAll(pageRequest.getPageable());
	}

}
