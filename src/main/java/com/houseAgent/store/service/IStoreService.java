package com.houseAgent.store.service;

import com.houseAgent.store.domain.Store;

public interface IStoreService {
	public void save(Store entity);
	public Store findOne(Long id);
}
