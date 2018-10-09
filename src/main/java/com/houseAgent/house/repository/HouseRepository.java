package com.houseAgent.house.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.houseAgent.house.domain.House;

@Repository
public interface HouseRepository  extends PagingAndSortingRepository<House, Long>
							,JpaSpecificationExecutor<House>{

}
