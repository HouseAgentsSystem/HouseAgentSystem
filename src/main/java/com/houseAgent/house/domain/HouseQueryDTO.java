package com.houseAgent.house.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


public class HouseQueryDTO {
	private String location;
	private Double price;//价格
	private Double floorSpace;
	private int room;//面积
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Double getFloorSpace() {
		return floorSpace;
	}
	public void setFloorSpace(Double floorSpace) {
		this.floorSpace = floorSpace;
	}
	public int getRoom() {
		return room;
	}
	public void setRoom(int room) {
		this.room = room;
	}
	@SuppressWarnings({ "serial"})
	public static Specification<House> getWhereClause(final HouseQueryDTO houseQueryDTO) {
		return new Specification<House>() {
			public Predicate toPredicate(Root<House> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
			
				List<Predicate> predicate = new ArrayList<>();
				System.out.println("123");
				if (StringUtils.isNotBlank(houseQueryDTO.getLocation())) {
					System.out.println(root.get("region").as(String.class));
					predicate.add(criteriaBuilder.like(root.get("region").as(String.class),
							"%" + houseQueryDTO.getLocation() + "%"));
				}
				if(0!=houseQueryDTO.getRoom()) {
					if(houseQueryDTO.getRoom()==50) {
						predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("area"),
								houseQueryDTO.getRoom()));
					}
					if(houseQueryDTO.getRoom()==70) {
						predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("area"),
								70));
						predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("area"),
								51));
					}
					if(houseQueryDTO.getRoom()==100) {
						predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("area"),
								100));
						predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("area"),
								71));
					}
					if(houseQueryDTO.getRoom()==150) {
						predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("area"),
								150));
						predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("area"),
								101));
					}
					if(houseQueryDTO.getRoom()==180) {
						predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("area"),
								180));
						predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("area"),
								151));
					}
					if(houseQueryDTO.getRoom()==181) {
						predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("area"),
								181));
					}
				}
				if (0!=houseQueryDTO.getPrice()) {
					if(houseQueryDTO.getPrice()==50) {
						predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"),
								houseQueryDTO.getPrice()));
					}
					if(houseQueryDTO.getPrice()==100) {
						predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"),
								100));
						predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"),
								51));
					}
					if(houseQueryDTO.getPrice()==150) {
						predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"),
								150));
						predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"),
								101));
					}
					if(houseQueryDTO.getPrice()==300) {
						predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"),
								300));
						predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"),
								151));
					}
					if(houseQueryDTO.getPrice()==500) {
						predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"),
								500));
						predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"),
								301));
					}
					if(houseQueryDTO.getPrice()==1000) {
						predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"),
								1000));
						predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"),
								501));
					}
					if(houseQueryDTO.getPrice()==1001) {
						predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"),
								1001));
					}
					System.out.println(houseQueryDTO.getPrice());
				}
				
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}

		};
	}
}
