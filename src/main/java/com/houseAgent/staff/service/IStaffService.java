package com.houseAgent.staff.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.houseAgent.staff.domain.Staff;

public interface IStaffService {
	public void saveAndUpdate(Staff staff);
	
	public void deleteById(Long id);
	public void deleteAll(Long[] ids);
	
	public Staff findOne(long id);
	public Page<Staff> findAll(Specification<Staff> spec, Pageable pageable);
}
