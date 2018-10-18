package com.houseAgent.houserent.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.houseAgent.houserent.domain.HouseRent;
import com.houseAgent.houserent.domain.HouseRentBaseDTO;
import com.houseAgent.houserent.domain.ShowHouseRentDTO;


public interface IHouseRentService {
	public Page<HouseRentBaseDTO> findAll(Pageable pageable);
	public void saveAndUpdate(HouseRent houseRent);
	public Page<ShowHouseRentDTO> findAll(Specification<HouseRent> spec,Pageable pageable);
	public HouseRent findOne(Long id);
	public void delete(Long id);
}
