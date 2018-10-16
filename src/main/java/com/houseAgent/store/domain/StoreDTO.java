package com.houseAgent.store.domain;

public class StoreDTO {
	private Long id;//id
	private String storeName;//店名
	private String address;//地址
	private String managerName;
	private String managerPhone;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerPhone() {
		return managerPhone;
	}
	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}

	@Override
	public String toString() {
		return "StoreDTO [id=" + id + ", storeName=" + storeName + ", address=" + address + ", managerName=" + managerName
				+ ", managerPhone=" + managerPhone + "]";
	}
}
