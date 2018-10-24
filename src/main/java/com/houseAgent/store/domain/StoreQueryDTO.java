package com.houseAgent.store.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.houseAgent.staff.domain.Staff;

public class StoreQueryDTO {
	private String storeName;
	private String managerName;
	private String region;//地区
	
	@SuppressWarnings("unused")
	public static Specification<Store> getWhereClause(StoreQueryDTO storeQueryDTO)
    {
		return new Specification<Store>() {
			public Predicate toPredicate(Root<Store> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				 //1.声明Predicate集合
				 List<Predicate> predicate = new ArrayList<>();
				 //2.根据storeQueryDTO查询条件动态添加Predicate
				 System.out.println(storeQueryDTO.getStoreName());
				 if(StringUtils.isNotBlank(storeQueryDTO.getStoreName())) {
					 predicate.add(cb.like(root.get("storeName").as(String.class),"%"+ storeQueryDTO.getStoreName()+"%"));
				 }
				 
				 if(StringUtils.isNotBlank(storeQueryDTO.getManagerName())) {
					 //CriteriaQuery<?> criteriaQuery = cb.createQuery(Staff.class);
					 //query.
					 //bancp
//					 Root<Staff> staffRoot = query.from(Staff.class);
//					 Join<Staff, Store> storeJoin = staffRoot.join(staffRoot.getModel().getSingularAttribute("store", Store.class), JoinType.LEFT);
//					 predicate.add(cb.like(staffRoot.get("realname").as(String.class),"%"+ StoreQueryDTO.getManagerName()+"%"));
//					 //predicate.add(cb.equal(staffRoot.get("realname"), StoreQueryDTO.getManagerName()));
//					 predicate.add(cb.equal(staffRoot.get("position"), "经理"));
//					 System.out.println(storeJoin);
					 
					 Root<Staff> staffRoot = query.from(Staff.class);
					 predicate.add(cb.equal(root.get("id"), staffRoot.get("store").get("id")));
					 predicate.add(cb.like(staffRoot.get("realName").as(String.class),"%"+ storeQueryDTO.getManagerName()+"%"));
//					 predicate.add(cb.equal(staffRoot.get("realname"), StoreQueryDTO.getManagerName()));
					 predicate.add(cb.equal(staffRoot.get("position"), "经理"));
					 
					 //predicate.add(
//					 storeJoin.on(
//							 cb.and(
//									cb.equal(staffRoot.get("store").get("id"), storeJoin.get("id")),
//									cb.equal(staffRoot.get("realname"), StoreQueryDTO.getManagerName()),
//									cb.equal(staffRoot.get("position"), "经理")
//									 )
//							 );
					 //predicate.add(cb.equal(staffRoot.get("realname"), StoreQueryDTO.getManagerName()));
					 //predicate.add(cb.equal(staffRoot.get("position"), "经理"));
					 
					 //predicate.add(cb.equal(root.get("id"), storeJoin.get("id")));
					 //criteriaQuery.select();
					 //return ;
					 //criteriaQuery.where(cb.equal(staffRoot.get("store").get("id"), storeJoin.get("id")));
					 //predicate.add(cb.exists(criteriaQuery));
					 //predicate.add(cb.equal(staffRoot.get("store").get("id"), storeJoin.get("id")));
//					 predicate.add(cb.like(staffRoot.get("realname").as(String.class),"%"+ StoreQueryDTO.getManagerName()+"%"));
//					 //predicate.add(cb.equal(staffRoot.get("realname"), StoreQueryDTO.getManagerName()));
//					 predicate.add(cb.equal(staffRoot.get("position"), "经理"));
//					 System.out.println(storeJoin);
					 //criteriaQuery.select(staffRoot);
					 //predicate.add(cb.staffRoot);
					 //predicate.add(storeJoin.toString());
					 
					 //);
//					 Join<Store, Staff> join = root.join("store_id", JoinType.LEFT);
//					 //predicate.add(cb.equal(root, join.get("store")));
//					 predicate.add(cb.equal(join.get("realname"), StoreQueryDTO.getManagerName()));
//					 predicate.add(cb.equal(join.get("position"), "经理"));
					 //predicate.add(Ex);
					 //...
					 //Root<Staff> staffRoot = new Root<Staff>();
					 //predicate.add(cb.equal(root, cb.equal(staffRoot.get("storeName").as(String.class) , y));
					 //predicate.add();
					 
//					 CriteriaQuery<Staff> criteriaQuery = cb.createQuery(Staff.class);
//					 Root<Staff> staffRoot = criteriaQuery.from(Staff.class);
//					 cb.equal(staffRoot.get("realname"), StoreQueryDTO.getManagerName());
//					 cb.equal(staffRoot.get("position"), "经理");
				 }
				 
				 if(StringUtils.isNotBlank(storeQueryDTO.getRegion())) {
					 predicate.add(cb.like(root.get("address").as(String.class),"%"+ storeQueryDTO.getRegion()+"%"));
				 }

				 //3.根据Predicate集合生成并返回and 连接的 where条件
	             return cb.and(predicate.toArray(new Predicate[predicate.size()]));
			
			}
		};
      
    }
	
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	
}
