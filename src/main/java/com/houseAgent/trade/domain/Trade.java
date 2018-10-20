package com.houseAgent.trade.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.houseAgent.house.domain.House;
import com.houseAgent.user.domain.User;


@Entity
@Table(name="t_trade")
public class Trade {
	private Long id;
	private Date saleDate;
	private Double agencyFees;
	private Double actualPrice;
	private User user;//买家
	private House houseData;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public Date getSaleDate() {
		return saleDate;
	}
	public Double getAgencyFees() {
		return agencyFees;
	}
	public Double getActualPrice() {
		return actualPrice;
	}
	@ManyToOne(cascade=CascadeType.REFRESH)
	public User getUser() {
		return user;
	}
	@ManyToOne(cascade=CascadeType.REFRESH)
	public House getHouseData() {
		return houseData;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}
	public void setAgencyFees(Double agencyFees) {
		this.agencyFees = agencyFees;
	}
	public void setActualPrice(Double actualPrice) {
		this.actualPrice = actualPrice;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setHouseData(House houseData) {
		this.houseData = houseData;
	}
}
