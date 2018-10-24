package com.houseAgent.staff.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.houseAgent.house.domain.House;
import com.houseAgent.house.domain.HouseDTO;
import com.houseAgent.house.repository.HouseRepository;
import com.houseAgent.staff.domain.Staff;
import com.houseAgent.staff.domain.StaffDTO;
import com.houseAgent.staff.repository.StaffRepository;
import com.houseAgent.store.domain.Store;



@Service
public class StaffService implements IStaffService{

	@Autowired
	private StaffRepository staffRepository;

	@Autowired
	private HouseRepository houseRepository;
	
	@Override
	public void save(Staff staff, Staff manager) {
		staff.setFaceImg("default.jpg");
		staff.setPassword("123456");//加密
		staff.setPosition("员工");
		staff.setStore(manager.getStore());
		staffRepository.save(staff);
	}
	
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
	public StaffDTO findOne(long id) {
		Staff staff = staffRepository.findById(id).get();
		StaffDTO staffDTO = new StaffDTO();
		StaffDTO.entityToDto(staff, staffDTO);
		return staffDTO;
	}

	@Override
	public Page<StaffDTO> findAll(Specification<Staff> spec, Pageable pageable) {
		Page<Staff> list = staffRepository.findAll(spec, pageable);
		
		List<StaffDTO> staffList = new ArrayList<StaffDTO>();
		for(Staff entity : list.getContent()) {
			StaffDTO dto = new StaffDTO();
			StaffDTO.entityToDto(entity, dto);
			staffList.add(dto);
		}
		return new PageImpl<StaffDTO>(staffList, pageable, list.getTotalElements());
	}
	
	@Override
	public void update(Long id, StaffDTO staffDTO) {
		Staff staff = staffRepository.findById(id).get();
		StaffDTO.dtoToEntity(staffDTO, staff);
		staffRepository.save(staff);
	}

	@Override
	public Staff findById(long id) {
		// TODO Auto-generated method stub
		return staffRepository.findById(id).get();
	}

	@Override
	public Page<HouseDTO> findHouseByStaffId(Long staffId, Pageable pageRequest) {
		Staff staff = staffRepository.findById(staffId).get();
		List<HouseDTO> dtoList = new ArrayList<>();
		List<House> houseList = houseRepository.findHouseByStaff(staff);
		for (House house : houseList) {
			HouseDTO dto = new HouseDTO();
			HouseDTO.entityToDto(house, dto);
			dtoList.add(dto);
		}
		return null;
	}
}
