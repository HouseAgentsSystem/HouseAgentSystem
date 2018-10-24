package com.houseAgent.staff.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.houseAgent.house.domain.HouseDTO;
import com.houseAgent.staff.domain.Staff;
import com.houseAgent.staff.domain.StaffDTO;



public interface IStaffService {
	public void save(Staff staff, Staff Manager);
	public void saveAndUpdate(Staff staff);
	public void update(String id, StaffDTO staffDTO);
	
	public void deleteById(String id);
	public void deleteAll(String[] ids);
	
	public StaffDTO findOne(String id);
	public Staff findById(String id);
	public Page<StaffDTO> findAll(Specification<Staff> spec, Pageable pageable);
	
	public Page<HouseDTO> findHouseByStaffId(String staffId, Pageable pageRequest);
}

