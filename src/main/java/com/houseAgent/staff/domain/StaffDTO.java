package com.houseAgent.staff.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.houseAgent.common.beans.BeanUtils;
import com.houseAgent.store.domain.Store;


public class StaffDTO {
	private long id;//员工Id	
	private	String realname;//员工姓名
	private String position;//职位
	private String phoneNumber;//手机号码
	private String sex;//性别
	private String faceImg;//头像
	
	private String storeId;//门店Id
	private String name;
	
	//后到前：1.针对“前端”设计的数据封装对象(查询)
	public static void entityToDto(Staff entity,StaffDTO dto){
		BeanUtils.copyProperties(entity, dto);
		if(entity.getStore()!=null) {
			dto.setName(entity.getStore().getStoreName());
		}
	}
	
	//前到后：2.维护多个对象 的数据 以及 对象之间的关联关系 (创建关联、更新关联)
	public static void dtoToEntity(StaffDTO dto, Staff entity) {
		BeanUtils.copyProperties(dto, entity);
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
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
