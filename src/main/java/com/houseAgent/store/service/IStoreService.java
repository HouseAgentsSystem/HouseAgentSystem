package com.houseAgent.store.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.houseAgent.common.web.ExtjsPageRequest;
import com.houseAgent.house.domain.House;
import com.houseAgent.staff.domain.Staff;
import com.houseAgent.store.domain.Store;
import com.houseAgent.store.domain.StoreDTO;

public interface IStoreService {
	
	public void saveAndUpdate(Store store);
	
	public void delete(Long id);
	public void deleteAll(Long[] ids);
	
	public Store findOne(long id);
	public Page<StoreDTO> findAll(Specification<Store> spec, Pageable pageable);

	public void entityToDto(Store store, StoreDTO storeDTO);

	public List<Staff> findStaffByStoreId(Long storeId);

	public Page<House> findHouseByStoreId(Long storeId, Pageable pageRequest);
	
	public void saveOneStore(Store store, Staff staff);
}
