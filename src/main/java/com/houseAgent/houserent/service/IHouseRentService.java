package com.houseAgent.houserent.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.houseAgent.houserent.domain.HouseRent;
import com.houseAgent.houserent.domain.HouseRentBaseDTO;


public interface IHouseRentService {
	public Page<HouseRentBaseDTO> findAll(Pageable pageable);
}
