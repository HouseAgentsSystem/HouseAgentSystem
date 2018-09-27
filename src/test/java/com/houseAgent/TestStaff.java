package com.houseAgent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.houseAgent.staff.domain.Staff;
import com.houseAgent.staff.service.StaffService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestStaff {
	
	@Autowired
	private StaffService staffService;
	
	@Test
	public void addStaff() {
		Staff s = new Staff();
		s.setRealname("洪老爷");
		s.setPhoneNumber("12345678910");
		s.setPosition("Boss");
		s.setSex("汉子");
		s.setFaceImg(null);
		s.setPassword(null);
		s.setStore(null);
		s.setUserName("洪牛逼");
		
		staffService.saveAndUpdate(s);
	}
	
	@Test
	public void testFind() {
		System.out.println(staffService.findOne(1L));
	}
	
}
