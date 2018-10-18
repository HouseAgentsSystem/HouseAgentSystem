package com.houseAgent.houserent.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.houseAgent.common.web.ExtjsPageRequest;
import com.houseAgent.houserent.domain.HouseRent;
import com.houseAgent.houserent.domain.HouseRentDTO;
import com.houseAgent.houserent.domain.ShowHouseRentDTO;
import com.houseAgent.houserent.domain.ShowHouseRentQueryDTO;
import com.houseAgent.houserent.service.IHouseRentService;

@Controller
@RequestMapping("/showHouseRent")
public class ShowHouseRentController {

	@Autowired
	private IHouseRentService houseRentService;
	
	@GetMapping
	public @ResponseBody Page<ShowHouseRentDTO> getPage(ShowHouseRentQueryDTO showHouseRentQueryDTO, ExtjsPageRequest pageRequest) 
	{
		return houseRentService.findAll(ShowHouseRentQueryDTO.getWhereClause(showHouseRentQueryDTO), pageRequest.getPageable());
	}
	
	@RequestMapping("/houseRentDetail/{id}")
	public String getHouseRentDetail(@PathVariable("id") Long id, Map<String, Object> model) {
		HouseRent houseRent = houseRentService.findOne(id);
		HouseRentDTO houseRentDTO = new HouseRentDTO();
		HouseRentDTO.entityToDto(houseRent, houseRentDTO);
		model.put("houseRent", houseRentDTO);
		return "houserentdetail";
	}
}
