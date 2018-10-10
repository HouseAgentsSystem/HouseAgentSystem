package com.houseAgent.houserent.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.houseAgent.houserent.domain.HouseRent;



public interface HouseRentRepository extends PagingAndSortingRepository<HouseRent, Long>, JpaSpecificationExecutor<HouseRent> {

}
