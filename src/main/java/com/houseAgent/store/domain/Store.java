package com.houseAgent.store.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.houseAgent.staff.domain.Staff;


@Entity
@Table(name = "t_store")
public class Store {
	private Long id;//id
	private String storeName;//店名
	private String address;//地址
	//private List<Staff> staffs = null;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public String getStoreName() {
		return storeName;
	}
	public String getAddress() {
		return address;
	}
//	@OneToMany(mappedBy="store")
//	public List<Staff> getStaffs() {
//		return staffs;
//	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public void setAddress(String address) {
		this.address = address;
	}
//	public void setStaffs(List<Staff> staffs) {
//		//this.staffs = new ArrayList<Staff>();
//		this.staffs = staffs;
//	}
}
