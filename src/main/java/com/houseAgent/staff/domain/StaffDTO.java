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

public class StaffDTO {
	private String id;			//员工Id	
	private String password;	//员工密码
	private	String realName;	//员工姓名
	private String position;	//职位
	private String phoneNumber;	//手机号码
	private String sex;			//性别
	private String faceImg;		//头像
	
	private Long storeId;		//门店Id
	private String name;		//门店名
	
	//后到前：1.针对“前端”设计的数据封装对象(查询)
	public static void entityToDto(Staff entity,StaffDTO dto){
		BeanUtils.copyProperties(entity, dto);
		if(entity.getStore()!=null) {
			dto.setStoreId(entity.getStore().getId());
			dto.setName(entity.getStore().getStoreName());
		}
	}
	
	//前到后：2.维护多个对象 的数据 以及 对象之间的关联关系 (创建关联、更新关联)
	public static void dtoToEntity(StaffDTO dto, Staff entity) {
		System.out.println(dto.getPassword());
//		if(StringUtils.isNotBlank(dto.getPassword())) {
//			entity.setPassword(password);
//		}
		System.out.println(entity.getPassword());
		BeanUtils.copyProperties(dto, entity);
		System.out.println(entity.getPassword());
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
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
	
	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	
	@SuppressWarnings({ "serial"})
	public static Specification<Staff> getWhereClause(final StaffDTO staffDTO) {
		return new Specification<Staff>() {
			@Override
			public Predicate toPredicate(Root<Staff> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
			
				List<Predicate> predicate = new ArrayList<>();
				if (StringUtils.isNotBlank(staffDTO.getRealName())) {
					predicate.add(criteriaBuilder.like(root.get("realName").as(String.class),
							"%"+staffDTO.getRealName()+"%"));
				}
				if (StringUtils.isNotBlank(staffDTO.getSex())) {
					predicate.add(criteriaBuilder.like(root.get("sex").as(String.class),
							staffDTO.getSex()));
				}
				if(staffDTO.getStoreId() != null) {
					predicate.add(criteriaBuilder.equal(root.get("store").get("id"), staffDTO.getStoreId()));
					predicate.add(criteriaBuilder.equal(root.get("position"), "员工"));
				} else {
					predicate.add(criteriaBuilder.notEqual(root.get("position"), "管理员"));
				}
				
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}
}
