package com.houseAgent.staff.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.houseAgent.staff.domain.Staff;

public interface StaffRepository extends PagingAndSortingRepository<Staff, Long>,JpaSpecificationExecutor<Staff>{

}
