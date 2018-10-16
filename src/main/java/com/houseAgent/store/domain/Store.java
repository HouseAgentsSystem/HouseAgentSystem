package com.houseAgent.store.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_store")
public class Store {
	private Long id;//id
	private String storeName;//店名
	private String address;//地址
	
	@Id
	//@Column(name = "store_id")
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
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
