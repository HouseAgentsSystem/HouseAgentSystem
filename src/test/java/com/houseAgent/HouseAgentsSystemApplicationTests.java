package com.houseAgent;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.houseAgent.staff.domain.Staff;
import com.houseAgent.staff.service.StaffService;
import com.houseAgent.user.domain.User;
import com.houseAgent.user.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HouseAgentsSystemApplicationTests {
	@Autowired
	private UserService userService;
	
	//@Test
	public void addUser() {
		User user = new User();
		user.setUserName("admin");
		user.setPassword("admin");
		user.setPhoneNumber("1008611");
		user.setCreateTime(new Date());
		user.setFaceImage("image/1.jpg");
		userService.addUser(user);
	}
	@Test 
	public void login() {
		userService.login("admin1", "admin");
	}
	
}
