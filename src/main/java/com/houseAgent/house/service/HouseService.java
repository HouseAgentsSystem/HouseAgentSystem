package com.houseAgent.house.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.houseAgent.house.domain.House;
import com.houseAgent.house.repository.HouseRepository;
@Service
public class HouseService implements IHouseService {
	
	@Autowired
	private HouseRepository houseRepository;
	@Override
	public void addOneHouse(House entity) {
		houseRepository.save(entity);

	}

	@Override
	public Page<House> findAll(Specification<House> spec,Pageable pageable) {
		// TODO Auto-generated method stub
		return houseRepository.findAll(spec,pageable);
	}

	@Override
	public Optional<House> findOne(Long id) {
		// TODO Auto-generated method stub
		return houseRepository.findById(id);
	}

}
