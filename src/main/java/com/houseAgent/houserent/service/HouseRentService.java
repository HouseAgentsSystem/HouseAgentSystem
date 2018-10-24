package com.houseAgent.houserent.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.houseAgent.common.web.SessionUtil;
import com.houseAgent.houserent.domain.HouseRent;
import com.houseAgent.houserent.domain.HouseRentActivitiDTO;
import com.houseAgent.houserent.domain.HouseRentBaseDTO;
import com.houseAgent.houserent.domain.ShowHouseRentDTO;
import com.houseAgent.houserent.repository.HouseRentRepository;
import com.houseAgent.user.domain.User;

@Service
@Transactional
public class HouseRentService implements IHouseRentService {
	
	@Autowired
	private HouseRentRepository houseRentRepository;
	@Autowired
	private IHouseRentActivitiService houseRentActivitiService;
	@Override
	@Transactional(readOnly=true)
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

	@Override
	@Transactional(readOnly=true)
	public Page<ShowHouseRentDTO> findAll(Specification<HouseRent> spec, Pageable pageable) {
		Page<HouseRent> houseRentPage = houseRentRepository.findAll(spec, pageable);
		
		List<ShowHouseRentDTO> dtoList = new ArrayList<>();
		List<HouseRent> entityList = houseRentPage.getContent();
		for (HouseRent entity : entityList) {
			ShowHouseRentDTO dto = new ShowHouseRentDTO();
			ShowHouseRentDTO.entityToDto(entity, dto);
			dtoList.add(dto);
		}
		return new PageImpl<>(dtoList, pageable, houseRentPage.getTotalElements());
	}
	
	@Override
	@Transactional(readOnly=true)
	public Page<HouseRentActivitiDTO> findByUserId(Specification<HouseRent> spec, Pageable pageable) {
		
		Page<HouseRent> houseRentPage = houseRentRepository.findAll(spec, pageable);
		
		List<HouseRentActivitiDTO> dtoList = new ArrayList<>();
		List<HouseRent> entityList = houseRentPage.getContent();
		for (HouseRent entity : entityList) {
			HouseRentActivitiDTO dto = new HouseRentActivitiDTO();
			HouseRentActivitiDTO.entityToDto(entity, dto);
			dtoList.add(dto);
		}
		return new PageImpl<>(dtoList, pageable, houseRentPage.getTotalElements());
	}
	
	@Override
	@Transactional(readOnly=true)
	public HouseRent findOne(Long id) {
		return houseRentRepository.findById(id).get();
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		houseRentRepository.deleteById(id);
	}

	@Override
	public void save(User user, HouseRentActivitiDTO dto) {
		HouseRent entity = new HouseRent();
		HouseRentActivitiDTO.dtoToEntity(dto, entity);
		entity.setUser(user);
		String userId = user.getId().toString();
		houseRentRepository.save(entity);
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("applyUserId", userId);
		houseRentActivitiService.startWorkflow(userId,entity.getId(), variables);
	}
}
