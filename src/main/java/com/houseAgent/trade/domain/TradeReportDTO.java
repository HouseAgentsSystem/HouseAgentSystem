package com.houseAgent.trade.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.houseAgent.common.beans.BeanUtils;

public class TradeReportDTO {
	
	private Date saleDate;
	private Double actualPrice;
	private String storeName;
	private String staffName;
	private Double agencyFees;
	
	public static void entityToDto(Trade entity, TradeReportDTO dto) {
		BeanUtils.copyProperties(entity, dto);
		dto.setStoreName(entity.getHouseData().getStore().getStoreName());
		dto.setStaffName(entity.getHouseData().getStaff().getUserName());
//		dto.setStaffName(entity.getHouseData().getStaff().getId());
	}
//	public static void dtoToEntity(TradeReportDTO dto, Trade entity) {
//		
//	}
	@Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	public Date getSaleDate() {
		return saleDate;
	}
	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}
	public Double getActualPrice() {
		return actualPrice;
	}
	public void setActualPrice(Double actualPrice) {
		this.actualPrice = actualPrice;
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
	public Double getAgencyFees() {
		return agencyFees;
	}
	public void setAgencyFees(Double agencyFees) {
		this.agencyFees = agencyFees;
	}
	@Override
	public String toString() {
		return "TradeReportDTO [saleDate=" + saleDate + ", actualPrice=" + actualPrice + ", storeName=" + storeName
				+ ", staffName=" + staffName + ", agencyFees=" + agencyFees + "]";
	}
}
