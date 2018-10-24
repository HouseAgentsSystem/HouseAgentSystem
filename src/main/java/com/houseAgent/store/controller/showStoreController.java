package com.houseAgent.store.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.houseAgent.common.web.ExtjsPageRequest;
import com.houseAgent.house.domain.House;
import com.houseAgent.house.domain.HouseDTO;
import com.houseAgent.staff.domain.Staff;
import com.houseAgent.staff.domain.StaffDTO;
import com.houseAgent.store.domain.Store;
import com.houseAgent.store.domain.StoreDTO;
import com.houseAgent.store.service.IStoreService;

@Controller
@RequestMapping("/showStore")
public class showStoreController {
	
	@Autowired
	private IStoreService storeService;

	//获取单个门店信息
	@GetMapping("/getStore/{storeId}")
	public String getStore(@PathVariable("storeId")Long storeId, Map<String,Object> model) {
		Store store = storeService.findOne(storeId);
		StoreDTO storeDTO = new StoreDTO();
		storeService.entityToDto(store, storeDTO);
		model.put("store", storeDTO);
		return "storedetail";
	}
	
	//获取门店房源列表
	@RequestMapping(value="/getHouse/{storeId}")//, consumes=MediaType.APPLICATION_JSON_VALUE
	public @ResponseBody Page<HouseDTO> getHouseByStoreId(@PathVariable("storeId")Long storeId, ExtjsPageRequest pageRequest) {
		System.out.println("gethouse");
		return storeService.findHouseByStoreId(storeId, pageRequest.getPageable());
	}
	
	//获取门店下的员工
	@RequestMapping(value="/getStaff/{storeId}")//, consumes=MediaType.APPLICATION_JSON_VALUE
	public @ResponseBody List<StaffDTO> getStaff(@PathVariable("storeId")Long storeId) {
		return storeService.findStaffByStoreId(storeId);
	}
}
