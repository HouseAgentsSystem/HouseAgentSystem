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
	public User login(String userNameOrPhoneNumber, String password) {
		User user = userRepository.Login(userNameOrPhoneNumber, password);
		if (user == null) {
			user = userRepository.Login2(userNameOrPhoneNumber, password);
			if(user == null) {
				System.out.println("密码或账号输入错误");
				return user;
			}else 
			{
				System.out.println("登录成功");
				return user;
			}
		}else {
			System.out.println("登录成功");
			return user;
			}

	}

	@Override
	public User findByPhoneNumber(String phoneNumber) {
		// TODO Auto-generated method stub
		return userRepository.findByPhoneNumber(phoneNumber);
	}

}
