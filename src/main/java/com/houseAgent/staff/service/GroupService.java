package com.houseAgent.staff.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.houseAgent.staff.domain.Group;
import com.houseAgent.staff.repository.GroupRepository;
@Service
public class GroupService implements IGroupService {

	@Autowired
	GroupRepository groupRepository;
	@Override
	public Group findById(String id) {
		return groupRepository.findById(id).get();
	}

}
