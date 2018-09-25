package com.example.demo.user.service;

import com.example.demo.user.domain.User;

public interface IUserService {
	public void addUser(User entity);
	public void login(String userName,String password);
}
