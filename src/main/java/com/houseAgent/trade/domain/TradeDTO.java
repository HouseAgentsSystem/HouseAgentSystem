package com.houseAgent.trade.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.houseAgent.common.beans.BeanUtils;
import com.houseAgent.house.domain.House;
import com.houseAgent.user.domain.User;

public class TradeDTO {
	private Long id;
	private Date saleDate;
	private Double agencyFees;
	private Double actualPrice;
	
	private String buyerName;
	private String buyerPhoneNumber;
	
	private String ownerName;
	private String ownerPhoneNumber;
	private String houseRegion;
	private String houseAddress;
	private String houseSummary;//房子概要=几室几厅的多少平的房型
	
	private String storeName;
	private String staffName;
	
	public static void entityToDto(Trade entity, TradeDTO dto) {
		BeanUtils.copyProperties(entity, dto);
		
		User buyer = entity.getUser();
		House house = entity.getHouseData();
		
		dto.buyerName = buyer.getRealname();
		dto.buyerPhoneNumber = buyer.getPhoneNumber();
		
		dto.ownerName = house.getOwnerName();
		dto.ownerPhoneNumber = house.getOwnerPhoneNumber();
		dto.houseRegion = house.getRegion();
		dto.houseAddress = house.getAddress();
		dto.houseSummary = house.getRegion() + "室" + house.getHall() + "厅的" + house.getArea() + "平方米" + house.getType();
		dto.storeName = house.getStore().getStoreName();
		dto.staffName = house.getStaff().getRealName();
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
	public Date getSaleDate() {
		return saleDate;
	}
	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}
	public Double getAgencyFees() {
		return agencyFees;
	}
	public void setAgencyFees(Double agencyFees) {
		this.agencyFees = agencyFees;
	}
	public Double getActualPrice() {
		return actualPrice;
	}
	public void setActualPrice(Double actualPrice) {
		this.actualPrice = actualPrice;
	}
	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	public String getBuyerPhoneNumber() {
		return buyerPhoneNumber;
	}
	public void setBuyerPhoneNumber(String buyerPhoneNumber) {
		this.buyerPhoneNumber = buyerPhoneNumber;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getOwnerPhoneNumber() {
		return ownerPhoneNumber;
	}
	public void setOwnerPhoneNumber(String ownerPhoneNumber) {
		this.ownerPhoneNumber = ownerPhoneNumber;
	}
	public String getHouseRegion() {
		return houseRegion;
	}
	public void setHouseRegion(String houseRegion) {
		this.houseRegion = houseRegion;
	}
	public String getHouseAddress() {
		return houseAddress;
	}
	public void setHouseAddress(String houseAddress) {
		this.houseAddress = houseAddress;
	}
	public String getHouseSummary() {
		return houseSummary;
	}
	public void setHouseSummary(String houseSummary) {
		this.houseSummary = houseSummary;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	
	@Override
	public String toString() {
		return "TradeDTO [id=" + id + ", saleDate=" + saleDate + ", agencyFees=" + agencyFees + ", actualPrice="
				+ actualPrice + ", buyerName=" + buyerName + ", buyerPhoneNumber=" + buyerPhoneNumber + ", ownerName="
				+ ownerName + ", ownerPhoneNumber=" + ownerPhoneNumber + ", houseRegion=" + houseRegion
				+ ", houseAddress=" + houseAddress + ", houseSummary=" + houseSummary + ", storeName=" + storeName
				+ ", staffName=" + staffName + "]";
	}
}
