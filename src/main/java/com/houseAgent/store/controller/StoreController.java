package com.houseAgent.store.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.houseAgent.common.beans.BeanUtils;
import com.houseAgent.common.web.ExtAjaxResponse;
import com.houseAgent.common.web.ExtjsPageRequest;
import com.houseAgent.house.domain.House;
import com.houseAgent.staff.domain.Staff;
import com.houseAgent.store.domain.Store;
import com.houseAgent.store.domain.StoreDTO;
import com.houseAgent.store.domain.StoreQueryDTO;
import com.houseAgent.store.service.IStoreService;

@RestController
@RequestMapping("/store")
public class StoreController {
	
	@Autowired
	private IStoreService storeService;
	
	//获取门店列表
	@GetMapping
	public Page<StoreDTO> getPage(StoreQueryDTO storeQueryDTO, ExtjsPageRequest pageRequest) {
		return storeService.findAll(StoreQueryDTO.getWhereClause(storeQueryDTO), pageRequest.getPageable());
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public ExtAjaxResponse save(@RequestBody Store store) {
		try {
			//System.out.println(dto);
			//Store store = new Store();
			//BeanUtils.copyProperties(dto, store);
			storeService.saveAndUpdate(store);
			return new ExtAjaxResponse(true, "添加门店成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return new ExtAjaxResponse(false, "添加门店失败！");
		}
	}
	
	@PostMapping("/addStore")
	public ExtAjaxResponse addStore(Store store, Staff staff, String userName) {
		try {
			storeService.saveOneStore(store, staff, userName);
			
			return new ExtAjaxResponse(true, "添加门店成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return new ExtAjaxResponse(false, "添加门店失败！");
		}
	}
	
	@DeleteMapping(value = "{id}")
	public ExtAjaxResponse delete(@PathVariable("id") Long id) {
		try {
			storeService.delete(id);
			return new ExtAjaxResponse(true, "删除门店成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return new ExtAjaxResponse(false, "删除门店失败！");
		}
	}
	
	@PostMapping("/deletes")
	public ExtAjaxResponse deleteRows(@RequestParam(name="ids") Long[] ids) {
		try {
			if(ids != null) {
				storeService.deleteAll(ids);
			}
			return new ExtAjaxResponse(true, "批量删除成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "批量删除失败！");
		}
	}
	
	@PutMapping(value="{id}")
	public ExtAjaxResponse Update(@PathVariable("id") Long id, @RequestBody Store store) {
		try {
			Store entity = storeService.findOne(id);
			BeanUtils.copyProperties(store, entity);
			storeService.saveAndUpdate(entity);
			return new ExtAjaxResponse(true, "修改门店成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return new ExtAjaxResponse(false, "修改门店失败！");
		}
	}
	
}
