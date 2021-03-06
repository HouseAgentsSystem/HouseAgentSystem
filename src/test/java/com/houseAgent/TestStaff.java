package com.houseAgent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.houseAgent.house.domain.House;
import com.houseAgent.house.service.HouseService;
import com.houseAgent.staff.domain.Staff;
import com.houseAgent.staff.service.StaffService;
import com.houseAgent.store.domain.Store;
import com.houseAgent.store.service.StoreService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestStaff {
	
	@Autowired
	private StaffService staffService;
	@Autowired
	private StoreService storeService;
	@Autowired
	private HouseService houseService;
	
	@Test
	public void findOne() {
		System.out.println(storeService.findOne(1L));
	}
	
	//@Test
	public void addStore() {
		Store store = new Store();
		store.setAddress("东莞理工学院");
		store.setStoreName("乐家");
		storeService.saveAndUpdate(store);
		
	}
	//@Test
	public void addStaff() {
		Staff s = new Staff();
//		s.setRealname("鸡儿硬");
		s.setPhoneNumber("12345678910");
		s.setPosition("Boss");
		s.setSex("汉子");
		s.setFaceImg(null);
		s.setPassword(null);
		s.setStore(storeService.findOne(1L));
//		s.setUserName("邦邦硬");
		
		staffService.saveAndUpdate(s);
	}
	@Test
	public void addHouse() {
		House house = new House();
		//Staff staff = staffService.findOne(1L);
		//Store store = staff.getStore();
		house.setTitle("震惊，鸡儿邦邦硬!!!");
		house.setType("商品房");
		house.setRegion("东城");
		house.setImages("1.jpg");
		house.setPrice(300.0);
		house.setAddress("松山湖大学路东莞理工学院教师村");
		house.setImages("2.jpg");
		//house.setStaff(staff);
		//house.setStore(store);
		house.setArea(400.0);
		house.setAgencyFees(300.0);
		houseService.addOneHouse(house);
	}
	//@Test
	public void testFind() {
		System.out.println();
	}
	
}
