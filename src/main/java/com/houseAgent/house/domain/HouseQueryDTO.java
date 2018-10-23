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
	private Double price=0.0;//价格
	private Integer roomArea=0;//面积
	private Integer room=0;//几室
	private Integer state;
	private Integer distribution =0;//分配情况
	@SuppressWarnings({ "unused"})
	public static Specification<House> getWhereClause(final HouseQueryDTO houseQueryDTO) {
		return new Specification<House>() {
			public Predicate toPredicate(Root<House> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
			
				List<Predicate> predicate = new ArrayList<>();
				if (StringUtils.isNotBlank(houseQueryDTO.getLocation())) {
					predicate.add(criteriaBuilder.like(root.get("region").as(String.class),
							"%" + houseQueryDTO.getLocation() + "%"));
				}
				if(0!=houseQueryDTO.getDistribution()) {
					if(houseQueryDTO.getDistribution()==2) {
						predicate.add(criteriaBuilder.isNotNull(root.get("staff")));
					}
					if(houseQueryDTO.getDistribution()==1) {
						predicate.add(criteriaBuilder.isNull(root.get("staff")));
					}
				}
				if(0!=houseQueryDTO.getRoomArea()) {
					if(houseQueryDTO.getRoomArea()==50) {
						predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("area"),
								houseQueryDTO.getRoomArea()));
					}
					if(houseQueryDTO.getRoomArea()==70) {
						predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("area"),
								70));
						predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("area"),
								50));
					}
					if(houseQueryDTO.getRoomArea()==100) {
						predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("area"),
								100));
						predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("area"),
								70));
					}
					if(houseQueryDTO.getRoomArea()==150) {
						predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("area"),
								150));
						predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("area"),
								100));
					}
					if(houseQueryDTO.getRoomArea()==180) {
						predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("area"),
								180));
						predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("area"),
								150));
					}
					if(houseQueryDTO.getRoomArea()==181) {
						predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("area"),
								180));
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
								50));
					}
					if(houseQueryDTO.getPrice()==150) {
						predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"),
								150));
						predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"),
								100));
					}
					if(houseQueryDTO.getPrice()==300) {
						predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"),
								300));
						predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"),
								150));
					}
					if(houseQueryDTO.getPrice()==500) {
						predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"),
								500));
						predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"),
								300));
					}
					if(houseQueryDTO.getPrice()==1000) {
						predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"),
								1000));
						predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"),
								500));
					}
					if(houseQueryDTO.getPrice()==1001) {
						predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"),
								1000));
					}
				}
				System.out.println(houseQueryDTO.getRoom());
				if (0!=houseQueryDTO.getRoom()) {
					if(houseQueryDTO.getRoom()==1) {
						predicate.add(criteriaBuilder.equal(root.get("room"),
								1));
					}
					if(houseQueryDTO.getRoom()==2) {
						predicate.add(criteriaBuilder.equal(root.get("room"),
								2));
					}
					if(houseQueryDTO.getRoom()==3) {
						predicate.add(criteriaBuilder.equal(root.get("room"),
								3));
					}
					if(houseQueryDTO.getRoom()==4) {
						predicate.add(criteriaBuilder.equal(root.get("room"),
								4));
					}
					if(houseQueryDTO.getRoom()==5) {
						predicate.add(criteriaBuilder.equal(root.get("room"),
								5));
					}
				}
				predicate.add(criteriaBuilder.equal(root.get("state"),
						1));
				System.out.println("4");
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}

		};
	}
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
	public int getRoomArea() {
		return roomArea;
	}
	public void setRoomArea(int roomArea) {
		this.roomArea = roomArea;
	}
	public int getRoom() {
		return room;
	}
	public void setRoom(int room) {
		this.room = room;
	}
	public Integer getDistribution() {
		return distribution;
	}
	public void setDistribution(Integer distribution) {
		this.distribution = distribution;
	}
	
}
