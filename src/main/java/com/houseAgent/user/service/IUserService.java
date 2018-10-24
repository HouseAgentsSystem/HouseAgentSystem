package com.houseAgent.user.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.houseAgent.staff.domain.StaffDTO;
import com.houseAgent.user.domain.User;

public interface IUserService {
	public void addUser(User entity);
	public User login(String userNameOrPhoneNumber,String password);
	public User findByPhoneNumber(String phoneNumber);
	public User findOne(Long id);
	public User register(User user);
	public void updataUser(User entity);
	public boolean checkUserName(String userName);
	public boolean checkPhoneNumber(String phoneNumber);
	
	public Page<User> findAll(Specification<User> spec, Pageable pageable);
	public void update(Long id,User user);
}
