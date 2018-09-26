package com.example.demo.store.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.demo.staff.domain.Staff;

@Entity
@Table(name = "t_store")
public class Store {
	private Long id;//id
	private String name;//店名
	private String location;//地址
	private List<Staff> staffs = null;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getLocation() {
		return location;
	}
	@OneToMany(cascade=CascadeType.ALL,mappedBy="store")
	public List<Staff> getStaffs() {
		return staffs;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public void setStaffs(List<Staff> staffs) {
		this.staffs = new ArrayList<Staff>();
		this.staffs = staffs;
	}
}
