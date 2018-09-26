package com.example.demo.staff.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.demo.store.domain.Store;

@Entity
@Table(name = "t_staff")
public class Staff {
	private Long id;//员工id
	private String userName;//账号
	private String password;//密码
	private String position;//职位
	private String phoneNumber;//手机号码
	private String sex;//性别
	private String faceImg;//头像
	private Store store;//门店Id
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getPosition() {
		return position;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getSex() {
		return sex;
	}

	public String getFaceImg() {
		return faceImg;
	}
	@ManyToOne()
	public Store getStore() {
		return store;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setFaceImg(String faceImg) {
		this.faceImg = faceImg;
	}

	public void setStore(Store store) {
		this.store = store;
	}
	
	
}
