package com.houseAgent.staff.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.houseAgent.staff.domain.Staff;
import com.houseAgent.staff.repository.StaffRepository;



@Service
public class StaffService implements IStaffService{

	@Autowired
	private StaffRepository staffRepository;
	
	@Override
	public void saveAndUpdate(Staff staff) {
		staffRepository.save(staff);
	}

	@Override
	public void deleteById(Long id) {
		staffRepository.deleteById(id);
		
	}

	@Override
	public void deleteAll(Long[] ids) {
		List<Long> idLists = new ArrayList<Long>(Arrays.asList(ids));
		
		List<Staff> staffs = (List<Staff>) staffRepository.findAllById(idLists);
		if(staffs!=null) {
			staffRepository.deleteAll(staffs);
		}
	}

	@Override
	public Staff findOne(long id) {
		return staffRepository.findById(id).get();
	}

	@Override
	public Page<Staff> findAll(Specification<Staff> spec, Pageable pageable) {
		return staffRepository.findAll(spec, pageable);
	}
}
