package com.houseAgent.user.service;

import com.houseAgent.user.domain.User;

public interface IUserService {
	public void addUser(User entity);
	public void login(String userName,String password);
}
