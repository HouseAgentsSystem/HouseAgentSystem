package com.houseAgent.house.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.houseAgent.house.domain.House;
import com.houseAgent.store.domain.Store;

@Repository
public interface HouseRepository  extends PagingAndSortingRepository<House, Long>
							,JpaSpecificationExecutor<House>{

	public List<House> findHouseByStore(Store store);
}
