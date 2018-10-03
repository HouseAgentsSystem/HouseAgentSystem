package com.houseAgent.user.service;

import com.houseAgent.user.domain.User;

public interface IUserService {
	public void addUser(User entity);
	public User login(String userNameOrPhoneNumber,String password);
	public User findByPhoneNumber(String phoneNumber);
}
