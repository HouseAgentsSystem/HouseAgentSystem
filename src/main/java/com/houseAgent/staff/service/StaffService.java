package com.houseAgent.staff.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.houseAgent.house.domain.House;
import com.houseAgent.house.domain.HouseDTO;
import com.houseAgent.house.repository.HouseRepository;
import com.houseAgent.staff.domain.Group;
import com.houseAgent.staff.domain.Staff;
import com.houseAgent.staff.domain.StaffDTO;
import com.houseAgent.staff.repository.GroupRepository;
import com.houseAgent.staff.repository.StaffRepository;



@Service
public class StaffService implements IStaffService{

	@Autowired
	private StaffRepository staffRepository;
	
	@Autowired
	private HouseRepository houseRepository;
	@Autowired
	private GroupRepository groupRepository;
	
	@Override
	public void save(Staff staff, Staff manager) {
		staff.setFaceImg("default.jpg");
		staff.setPassword("123456");//加密
		//密码加密	...MD5加密两次	... 盐值为 随机字符+userName
		String salt = new SecureRandomNumberGenerator().nextBytes(32).toHex();
		staff.setSalt(salt);
		String passwordR = new SimpleHash("MD5", staff.getPassword(), staff.getCredentialsSalt(), 2).toString();
		staff.setPassword(passwordR);
		staff.setPosition("员工");
		staff.setStore(manager.getStore());
		if(staff.getPosition()!=null) {
			Group group = groupRepository.findByName(staff.getPosition());
			List<Group> actIdGroups = new ArrayList<Group>();
			actIdGroups.add(group);
			staff.setActIdGroups(actIdGroups);
		}
		staffRepository.save(staff);
		
	}
	
	@Override
	public void saveAndUpdate(Staff staff) {
		if(staff.getPosition()!=null) {
			Group group = groupRepository.findByName(staff.getPosition());
			List<Group> actIdGroups = new ArrayList<Group>();
			actIdGroups.add(group);
			staff.setActIdGroups(actIdGroups);
		}
		staffRepository.save(staff);
	}

	@Override
	public void deleteById(String id) {
		staffRepository.deleteById(id);
		
	}

	@Override
	public void deleteAll(String[] ids) {
		List<String> idLists = new ArrayList<String>(Arrays.asList(ids));
		
		List<Staff> staffs = (List<Staff>) staffRepository.findAllById(idLists);
		if(staffs!=null) {
			staffRepository.deleteAll(staffs);
		}
	}

	@Override
	public StaffDTO findOne(String id) {
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
	public void update(String id, StaffDTO staffDTO) {
		Staff staff = staffRepository.findById(id).get();
		StaffDTO.dtoToEntity(staffDTO, staff);
		//判断password是否为空 不为空 则处理
		
		if(StringUtils.isNotBlank(staffDTO.getPassword())) {
			String salt = new SecureRandomNumberGenerator().nextBytes(32).toHex();
			staff.setSalt(salt);
			System.out.println(staff.getPassword());
			String passwordR = new SimpleHash("MD5", staff.getPassword(), staff.getCredentialsSalt(), 2).toString();
			staff.setPassword(passwordR);
		}
		staffRepository.save(staff);
	}

	@Override
	public Staff findById(String id) {
		// TODO Auto-generated method stub
		return staffRepository.findById(id).get();
	}

	@Override
	public Page<HouseDTO> findHouseByStaffId(String staffId, Pageable pageable) {
		Staff staff = staffRepository.findById(staffId).get();
		List<HouseDTO> dtoList = new ArrayList<>();
		List<House> houseList = houseRepository.findHouseByStaff(staff);
		for (House house : houseList) {
			HouseDTO dto = new HouseDTO();
			HouseDTO.entityToDto(house, dto);
			dtoList.add(dto);
		}
		return new PageImpl<HouseDTO>(dtoList, pageable, houseList.size());
	}
}
