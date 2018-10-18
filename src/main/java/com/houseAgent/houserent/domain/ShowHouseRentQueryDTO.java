package com.houseAgent.houserent.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.houseAgent.house.domain.House;
import com.houseAgent.house.domain.HouseQueryDTO;

public class ShowHouseRentQueryDTO {
	private String region;	//地区
    private Double rent;		//月租金
    private Integer room;		//室
    private String rentType;  //整租or合租
	
	public static Specification<HouseRent> getWhereClause(final ShowHouseRentQueryDTO showHouseRentQueryDTO) {
		return new Specification<HouseRent>() {
			public Predicate toPredicate(Root<HouseRent> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
			
				List<Predicate> predicate = new ArrayList<>();
				
				if (StringUtils.isNotBlank(showHouseRentQueryDTO.getRegion())) {
					System.out.println(root.get("region").as(String.class));
					predicate.add(cb.equal(root.get("region").as(String.class), 
							showHouseRentQueryDTO.getRegion()));
				}
				
				if (0 != showHouseRentQueryDTO.getRent()) {
					if(showHouseRentQueryDTO.getRent()==500) {
						predicate.add(cb.lessThanOrEqualTo(root.get("rent"),
								showHouseRentQueryDTO.getRent()));
					}
					if(showHouseRentQueryDTO.getRent()==800) {
						predicate.add(cb.lessThanOrEqualTo(root.get("rent"),
								800));
						predicate.add(cb.greaterThanOrEqualTo(root.get("rent"),
								501));
					}
					if(showHouseRentQueryDTO.getRent()==1000) {
						predicate.add(cb.lessThanOrEqualTo(root.get("rent"),
								1000));
						predicate.add(cb.greaterThanOrEqualTo(root.get("rent"),
								801));
					}
					if(showHouseRentQueryDTO.getRent()==1500) {
						predicate.add(cb.lessThanOrEqualTo(root.get("rent"),
								1500));
						predicate.add(cb.greaterThanOrEqualTo(root.get("rent"),
								1001));
					}
					if(showHouseRentQueryDTO.getRent()==2000) {
						predicate.add(cb.lessThanOrEqualTo(root.get("rent"),
								2000));
						predicate.add(cb.greaterThanOrEqualTo(root.get("rent"),
								1501));
					}
					if(showHouseRentQueryDTO.getRent()==3000) {
						predicate.add(cb.lessThanOrEqualTo(root.get("rent"),
								3000));
						predicate.add(cb.greaterThanOrEqualTo(root.get("rent"),
								2001));
					}
					if(showHouseRentQueryDTO.getRent()==5000) {
						predicate.add(cb.lessThanOrEqualTo(root.get("rent"),
								5000));
						predicate.add(cb.greaterThanOrEqualTo(root.get("rent"),
								3001));
					}
					if(showHouseRentQueryDTO.getRent()==5001) {
						predicate.add(cb.greaterThanOrEqualTo(root.get("rent"),
								5001));
					}
				}
				
				if(0 != showHouseRentQueryDTO.getRoom()) {
					if(showHouseRentQueryDTO.getRoom() != 5) {
						predicate.add(cb.equal(root.get("room"),
								showHouseRentQueryDTO.getRoom()));
					}
					if(showHouseRentQueryDTO.getRoom()==5) {
						predicate.add(cb.greaterThanOrEqualTo(root.get("room"),
								showHouseRentQueryDTO.getRoom()));
					}
				}
				
				if (StringUtils.isNotBlank(showHouseRentQueryDTO.getRentType())) {
					if(showHouseRentQueryDTO.getRentType().equals("整租")) {
						predicate.add(cb.isTrue(root.get("isEntireRent")));
					} else {
						predicate.add(cb.isFalse(root.get("isEntireRent")));
					}
				}
				
				return cb.and(predicate.toArray(new Predicate[predicate.size()]));
			}

		};
	}

	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public Double getRent() {
		return rent;
	}
	public void setRent(Double rent) {
		this.rent = rent;
	}
	public Integer getRoom() {
		return room;
	}
	public void setRoom(Integer room) {
		this.room = room;
	}
	public String getRentType() {
		return rentType;
	}
	public void setRentType(String rentType) {
		this.rentType = rentType;
	}
}
