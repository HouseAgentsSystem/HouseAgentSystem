package com.houseAgent.user.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.houseAgent.user.domain.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>,JpaSpecificationExecutor<User>{
	@Query("from User user where user.userName = ?1 and user.password = ?2 and user.status = 1") 
	public User Login(String userName,String password); 
	@Query("from User user where user.phoneNumber = ?1 and user.password = ?2 and user.status = 1") 
	public User Login2(String phoneNumber,String password); 
	@Query("from User user where user.phoneNumber = ?1 and user.status = 1") 
	public User findByPhoneNumber(String phoneNumber);
	@Query("from User user where user.userName = ?1 and user.status = 1") 
	public User findByUserName(String userName);
}
