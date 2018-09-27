package com.houseAgent.house.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.houseAgent.house.domain.House;

public interface IHouseService {
	public void addOneHouse(House entity);
	public Page<House> findAll(Pageable pageable);
	public Optional<House> findOne(Long id);
}
