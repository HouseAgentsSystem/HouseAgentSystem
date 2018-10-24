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
	public void update(Long id, StaffDTO staffDTO);
	
	public void deleteById(Long id);
	public void deleteAll(Long[] ids);
	
	public StaffDTO findOne(long id);
	public Staff findById(long id);
	public Page<StaffDTO> findAll(Specification<Staff> spec, Pageable pageable);
	
	public Page<HouseDTO> findHouseByStaffId(Long staffId, Pageable pageRequest);
}

