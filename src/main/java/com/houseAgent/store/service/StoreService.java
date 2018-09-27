package com.houseAgent.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.houseAgent.store.domain.Store;
import com.houseAgent.store.repository.StoreRepository;
@Service
public class StoreService implements IStoreService {
	@Autowired
	private StoreRepository storeRepository;
	@Override
	public void save(Store entity) {
		// TODO Auto-generated method stub
		storeRepository.save(entity);
	}
	@Override
	public Store findOne(Long id) {
		// TODO Auto-generated method stub
		return storeRepository.findById(id).get();
	}

}
