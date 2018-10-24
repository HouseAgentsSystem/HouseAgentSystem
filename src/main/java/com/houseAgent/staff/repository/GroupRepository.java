package com.houseAgent.staff.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.houseAgent.staff.domain.Group;
import com.houseAgent.staff.domain.Staff;

public interface GroupRepository extends PagingAndSortingRepository<Group, String>,JpaSpecificationExecutor<Group>{

	public Group findByName(String name);
}
