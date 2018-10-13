package com.houseAgent.houserent.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.houseAgent.houserent.domain.HouseRent;
import com.houseAgent.houserent.domain.HouseRentBaseDTO;
import com.houseAgent.houserent.repository.HouseRentRepository;

@Service
public class HouseRentService implements IHouseRentService {
	
	@Autowired
	private HouseRentRepository houseRentRepository;
	
	@Override
	public Page<HouseRentBaseDTO> findAll(Pageable pageable) {
		Page<HouseRent> list =  houseRentRepository.findAll(pageable);
		
		List<HouseRentBaseDTO> dtoLists = new ArrayList<HouseRentBaseDTO>();
		for (HouseRent entity : list.getContent()) {
			HouseRentBaseDTO dto = new HouseRentBaseDTO();
			HouseRentBaseDTO.entityToDto(entity, dto);
			dtoLists.add(dto);
		}
		
		return new PageImpl<HouseRentBaseDTO>(dtoLists, pageable, list.getTotalElements());
	}

}
