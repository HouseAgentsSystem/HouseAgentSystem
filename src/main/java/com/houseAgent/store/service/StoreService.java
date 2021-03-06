package com.houseAgent.store.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.houseAgent.common.beans.BeanUtils;
import com.houseAgent.house.domain.House;
import com.houseAgent.house.domain.HouseDTO;
import com.houseAgent.house.repository.HouseRepository;
import com.houseAgent.staff.domain.Group;
import com.houseAgent.staff.domain.Staff;
import com.houseAgent.staff.domain.StaffDTO;
import com.houseAgent.staff.repository.GroupRepository;
import com.houseAgent.staff.repository.StaffRepository;
import com.houseAgent.store.domain.Store;
import com.houseAgent.store.domain.StoreDTO;
import com.houseAgent.store.repository.StoreRepository;

@Service
@Transactional
public class StoreService implements IStoreService {

	@Autowired
	private StoreRepository storeRepository;
	@Autowired
	private StaffRepository staffRepository;
	@Autowired
	private HouseRepository houseRepository;
	@Autowired
	private GroupRepository groupRepository;
	
	@Override
	public void saveAndUpdate(Store store) {
		
		storeRepository.save(store);
	}

	@Override
	public void delete(Long id) {
		storeRepository.deleteById(id);
	}

	@Override
	public void deleteAll(Long[] ids) {
		List<Long> idLists = new ArrayList<Long>(Arrays.asList(ids));
		
		List<Store> stores = (List<Store>) storeRepository.findAllById(idLists);
		if(stores!=null) {
			storeRepository.deleteAll(stores);
		}
	}

	@Override
	@Transactional(readOnly=true)
	public Store findOne(long id) {
		return storeRepository.findById(id).get();
	}

	@Override
	@Transactional(readOnly=true)
	public Page<StoreDTO> findAll(Specification<Store> spec, Pageable pageable) {
		Page<Store> storePage = storeRepository.findAll(spec, pageable);
		
		List<StoreDTO> storeDTOList = new ArrayList<>();
		List<Store> storeList = storePage.getContent();
		for (Store store : storeList) {
			StoreDTO storeDTO = new StoreDTO();
			entityToDto(store, storeDTO);
			storeDTOList.add(storeDTO);
		}
		
		return new PageImpl<StoreDTO>(storeDTOList, pageable, storePage.getTotalElements());
	}
	
	public void entityToDto(Store store, StoreDTO dto) {
		BeanUtils.copyProperties(store, dto);
		Staff staff = staffRepository.findManagerByStoreId(store.getId());
		
		if(staff != null) {
			dto.setManagerName(staff.getRealName());
			dto.setManagerPhone(staff.getPhoneNumber());
		} else {
			dto.setManagerName("尚未分配");
			dto.setManagerPhone("-");
		}
	}

	@Override
	@Transactional(readOnly=true)
	public List<StaffDTO> findStaffByStoreId(Long storeId) {
		List<StaffDTO> dtos = new ArrayList<>();
		List<Staff> staffs = staffRepository.findStaffByStoreId(storeId);
		for (Staff staff : staffs) {
			StaffDTO dto = new StaffDTO();
			StaffDTO.entityToDto(staff, dto);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	@Transactional(readOnly=true)
	public Page<HouseDTO> findHouseByStoreId(Long storeId, Pageable pageable) {
		Store store = storeRepository.findById(storeId).get();
		List<HouseDTO> dtoList = new ArrayList<>();
		List<House> houseList = houseRepository.findHouseByStore(store);
		for (House house : houseList) {
			HouseDTO dto = new HouseDTO();
			HouseDTO.entityToDto(house, dto);
			dtoList.add(dto);
		}
		
		return new PageImpl<HouseDTO>(dtoList, pageable, houseList.size());
	}

	@Override
	public void saveOneStore(Store store, Staff staff, String userName) {
		storeRepository.save(store);
		staff.setId(userName);
		staff.setStore(store);
		staff.setPosition("经理");
		staff.setPassword("123456");//加密
		if(staff.getPosition()!=null) {
			Group group = groupRepository.findByName(staff.getPosition());
			List<Group> actIdGroups = new ArrayList<Group>();
			actIdGroups.add(group);
			staff.setActIdGroups(actIdGroups);
		}
		String salt = new SecureRandomNumberGenerator().nextBytes(32).toHex();
		staff.setSalt(salt);
		String passwordR = new SimpleHash("MD5", staff.getPassword(), staff.getCredentialsSalt(), 2).toString();
		staff.setPassword(passwordR);
		staff.setFaceImg("default.jpg");
		staffRepository.save(staff);
	}
}
