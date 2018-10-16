package com.houseAgent.house.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.houseAgent.house.domain.House;
import com.houseAgent.house.domain.HouseDTO;
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
	public Page<HouseDTO> findAll(Specification<House> spec,Pageable pageable) {
		Page<House> list =  houseRepository.findAll(spec, pageable);
		
		List<HouseDTO> dtoLists = new ArrayList<HouseDTO>();
		for (House entity : list.getContent()) {
			HouseDTO dto = new HouseDTO();
			HouseDTO.entityToDto(entity, dto);
			dtoLists.add(dto);
		}
		
		return new PageImpl<HouseDTO>(dtoLists, pageable, list.getTotalElements());
	}

	@Override
	public HouseDTO findOne(Long id) {
		// TODO Auto-generated method stub
		House house = houseRepository.findById(id).get();
		HouseDTO dto = new HouseDTO();
		HouseDTO.entityToDto(house, dto);
		return dto;
	}

	@Override
	public Page<HouseDTO> showAll(Pageable pageable) {
		Page<House> list =  houseRepository.findAll(pageable);
		
		List<HouseDTO> dtoLists = new ArrayList<HouseDTO>();
		for (House entity : list.getContent()) {
			HouseDTO dto = new HouseDTO();
			HouseDTO.entityToDto(entity, dto);
			dtoLists.add(dto);
		}
		
		return new PageImpl<HouseDTO>(dtoLists, pageable, list.getTotalElements());
	}

}
