package com.houseAgent.houserent.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.houseAgent.houserent.domain.HouseRent;
import com.houseAgent.houserent.domain.HouseRentActivitiDTO;
import com.houseAgent.houserent.domain.HouseRentBaseDTO;
import com.houseAgent.houserent.domain.HouseRentDTO;
import com.houseAgent.houserent.domain.ShowHouseRentDTO;
import com.houseAgent.user.domain.User;


public interface IHouseRentService {
	public Page<HouseRentBaseDTO> findAll(Pageable pageable);
	public Page<ShowHouseRentDTO> findAll(Specification<HouseRent> spec,Pageable pageable);
	public Page<HouseRentActivitiDTO> findByUserId(Specification<HouseRent> spec,Pageable pageable);
	public HouseRent findOne(Long id);
	public void delete(Long id);
	public void save(User user, HouseRentActivitiDTO houseRentDTO);
}
