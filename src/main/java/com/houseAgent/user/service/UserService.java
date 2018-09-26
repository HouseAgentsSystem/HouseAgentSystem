package com.houseAgent.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.houseAgent.user.domain.User;
import com.houseAgent.user.repository.UserRepository;

@Service
@Transactional
public class UserService implements IUserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void addUser(User entity) {
		userRepository.save(entity);
	}

	@Override
	public void login(String userName, String password) {
		User user = userRepository.Login(userName, password);
		if (user == null) {
			System.out.println("密码或账号输入错误");
		}else System.out.println("登录成功");

	}

}
