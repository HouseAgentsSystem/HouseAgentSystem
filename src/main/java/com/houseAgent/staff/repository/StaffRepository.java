package com.houseAgent.staff.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.houseAgent.staff.domain.Staff;

public interface StaffRepository extends PagingAndSortingRepository<Staff, Long>,JpaSpecificationExecutor<Staff>{

	@Query("from Staff staff where staff.store.id = ?1 and staff.position = '经理'")
	public Staff findManagerByStoreId(Long id);

	@Query("from Staff staff where staff.store.id = ?1 and staff.position = '员工'")
	public List<Staff> findStaffByStoreId(Long id);

}
