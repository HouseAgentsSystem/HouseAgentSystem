package com.houseAgent.house.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.houseAgent.house.domain.House;
import com.houseAgent.house.domain.HouseDTO;

public interface IHouseService {
	public void addOneHouse(House entity);
	public Page<HouseDTO> findAll(Specification<House> spec,Pageable pageable);
	public Page<HouseDTO> showAll(Pageable pageable);
	public HouseDTO findOne(Long id);
	public House findById(Long id);
	public void deleteById(Long id);
	public void deleteAll(Long [] ids);
	public void updata(House house);
}
