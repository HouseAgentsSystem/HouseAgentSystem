package com.houseAgent.staff.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.houseAgent.common.web.ExtjsPageRequest;
import com.houseAgent.house.domain.HouseDTO;
import com.houseAgent.staff.domain.Staff;
import com.houseAgent.staff.domain.StaffDTO;
import com.houseAgent.staff.service.IStaffService;

@Controller
@RequestMapping("/showStaffStore")
public class showStaffStoreController {
	
	@Autowired
	private IStaffService staffService;
	
	//获取员工信息
	@GetMapping("/getStaff/{staffId}")
	public String getStaff(@PathVariable("staffId")Long staffId, Map<String,Object> model) { 
		Staff staff = staffService.findById(staffId);
		StaffDTO staffDTO = new StaffDTO();
		StaffDTO.entityToDto(staff, staffDTO);
		model.put("staff", staffDTO);
		return "staffStore";
	}
	
	//获取门店房源列表
	@RequestMapping(value="/getHouse/{storeId}")//, consumes=MediaType.APPLICATION_JSON_VALUE
	public @ResponseBody Page<HouseDTO> getHouseByStoreId(@PathVariable("staffId")Long staffId, ExtjsPageRequest pageRequest) {
		System.out.println("gethouse");
		return staffService.findHouseByStaffId(staffId, pageRequest.getPageable());
	}
}
