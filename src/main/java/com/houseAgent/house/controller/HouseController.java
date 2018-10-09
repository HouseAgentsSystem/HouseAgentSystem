package com.houseAgent.house.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.houseAgent.common.web.ExtjsPageRequest;
import com.houseAgent.house.domain.House;
import com.houseAgent.house.domain.HouseQueryDTO;
import com.houseAgent.house.service.HouseService;


@RestController
@RequestMapping("/house")
public class HouseController {
	@Autowired
	private HouseService houseService;
	@GetMapping
	public Page<House> getPage(HouseQueryDTO houseQueryDTO ,ExtjsPageRequest pageRequest) 
	{
		return houseService.findAll(HouseQueryDTO.getWhereClause(houseQueryDTO),pageRequest.getPageable());
	}
}
