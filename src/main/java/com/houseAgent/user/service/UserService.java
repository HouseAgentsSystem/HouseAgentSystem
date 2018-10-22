package com.houseAgent.user.service;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
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
		//密码加密	...MD5加密两次	... 盐值为 随机字符+userName
		User user = userRepository.findByPhoneNumber(userNameOrPhoneNumber);
		if(user==null) {
			user = userRepository.findByUserName(userNameOrPhoneNumber);
			if(user==null) {
				return null;
			}
		}
		String passwordR = new SimpleHash("MD5", password, user.getCredentialsSalt(), 2).toString();
		if(passwordR.equals(user.getPassword())) {
			System.out.println("登录成功");
			return user;
		}
		System.out.println("密码或账号输入错误");
		return null;
	}

	@Override
	public User findByPhoneNumber(String phoneNumber) {
		// TODO Auto-generated method stub
		return userRepository.findByPhoneNumber(phoneNumber);
	}

	@Override
	public User findOne(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id).get();
	}

	@Override
	public User register(User entity) {
		if(userRepository.findByPhoneNumber(entity.getPhoneNumber())==null) {
			if(userRepository.findByPhoneNumber(entity.getUserName())==null) {
				if(userRepository.findByUserName(entity.getUserName())==null) {
					//密码加密	...MD5加密两次	... 盐值为 随机字符+userName
					String salt = new SecureRandomNumberGenerator().nextBytes(32).toHex();
					entity.setSalt(salt);
					String passwordR = new SimpleHash("MD5", entity.getPassword(), entity.getCredentialsSalt(), 2).toString();
					entity.setPassword(passwordR);
					User user = userRepository.save(entity);
					System.out.println("注册成功");
					return user;
				}
			}
		}
		return null;
	}
	/**
	 * 检查用户名是否可用
	 * @param userName
	 * @return
	 */
	@Override
	public boolean checkUserName(String userName) {
		if(userRepository.findByUserName(userName)==null) {
			return true;
		}
		return false;
	}
	/**
	 * 检查手机号码是否可用
	 * @param userName
	 * @return
	 */
	@Override
	public boolean checkPhoneNumber(String phoneNumber) {
		if(userRepository.findByPhoneNumber(phoneNumber)==null) {
			return true;
		}
		return false;
	}

	@Override
	public void updataUser(User entity) {
		userRepository.save(entity);
		
	}
	
}
