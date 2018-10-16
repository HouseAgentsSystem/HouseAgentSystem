package com.houseAgent.store.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.houseAgent.store.domain.Store;

public interface StoreRepository extends PagingAndSortingRepository<Store, Long>, JpaSpecificationExecutor<Store> {

}
