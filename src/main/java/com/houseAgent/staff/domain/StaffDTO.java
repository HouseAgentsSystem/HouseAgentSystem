package com.houseAgent.staff.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.houseAgent.store.domain.Store;


public class StaffDTO {
	private	String realname;//员工姓名
	private String position;//职位
	private String phoneNumber;//手机号码
	private String sex;//性别
	private String faceImg;//头像
	private Store store;//门店Id
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getFaceImg() {
		return faceImg;
	}
	public void setFaceImg(String faceImg) {
		this.faceImg = faceImg;
	}
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	
	@SuppressWarnings({ "serial"})
	public static Specification<Staff> getWhereClause(final StaffDTO staffDTO) {
		return new Specification<Staff>() {
			@Override
			public Predicate toPredicate(Root<Staff> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
			
				List<Predicate> predicate = new ArrayList<>();
				if (StringUtils.isNotBlank(staffDTO.getRealname())) {
					predicate.add(criteriaBuilder.like(root.get("realname").as(String.class),
							"%"+staffDTO.getRealname()+"%"));
				}
				if (StringUtils.isNotBlank(staffDTO.getSex())) {
					predicate.add(criteriaBuilder.like(root.get("sex").as(String.class),
							staffDTO.getSex()));
				}
				
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}
}
