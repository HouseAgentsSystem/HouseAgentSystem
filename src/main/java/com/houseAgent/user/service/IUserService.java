package com.houseAgent.user.service;

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
}
