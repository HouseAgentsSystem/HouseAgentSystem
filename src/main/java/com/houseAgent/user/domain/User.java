package com.houseAgent.user.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "t_user")
public class User {
	private Long id;
	private String userName;
	private String password;
	private String surname;//姓
	private String phoneNumber;
	private Date createTime;//创建日期
	private String FaceImage;//头像
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
	public String getSurname() {
		return surname;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	@Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy/MM/dd HH:mm:ss",timezone="GMT+8")
	public Date getCreateTime() {
		return createTime;
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
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getFaceImage() {
		return FaceImage;
	}
	public void setFaceImage(String faceImage) {
		FaceImage = faceImage;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", surname=" + surname
				+ ", phoneNumber=" + phoneNumber + ", createTime=" + createTime + ", FaceImage=" + FaceImage + "]";
	}
	
	
	
}
