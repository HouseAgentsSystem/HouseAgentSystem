package com.houseAgent.houserent.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.catalina.Host;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.houseAgent.common.web.ExtjsPageRequest;
import com.houseAgent.common.web.SessionUtil;
import com.houseAgent.house.domain.House;
import com.houseAgent.houserent.domain.HouseRent;
import com.houseAgent.houserent.domain.HouseRentActivitiDTO;
import com.houseAgent.houserent.domain.HouseRentDTO;
import com.houseAgent.houserent.domain.ShowHouseRentDTO;
import com.houseAgent.houserent.domain.ShowHouseRentQueryDTO;
import com.houseAgent.houserent.service.IHouseRentActivitiService;
import com.houseAgent.houserent.service.IHouseRentService;
import com.houseAgent.user.domain.User;

@Controller
@RequestMapping("/showHouseRent")
public class ShowHouseRentController {

	@Autowired
	private IHouseRentService houseRentService;
	@Autowired
	private IHouseRentActivitiService houseRentActivitiService;
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
	//添加租房申请
	@PostMapping("/addHouseRent")
	public String addHouse(HttpSession session, HouseRentActivitiDTO houseRentDTO, String images) {
		System.out.println("addHouse");
		System.out.println(houseRentDTO);
		System.out.println(images);
		houseRentDTO.setImgs(images.split(","));
		System.out.println(houseRentDTO.getImgs());
		User user = SessionUtil.getUser(session);
		System.out.println(user.getId());
		if(user.getId()!=null) {
			houseRentDTO.setApplyTime(new Date());
			houseRentService.save(user, houseRentDTO);
			return "addsucceed";
		}else {
			return "index";
		}
	}
}
