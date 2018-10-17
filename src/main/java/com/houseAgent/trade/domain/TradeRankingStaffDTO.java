package com.houseAgent.trade.domain;

import java.util.Date;


public class TradeRankingStaffDTO {
	
//	private Date saleDate;		//交易时间-----1月，2月，3月....
//	private Double agencyFees;	//中介费
	private Double total;		//该月实际售价
	private Long staffId;		//员工账号
	private String staffName;	//员工真实姓名
	private String storeName;
	public TradeRankingStaffDTO(Double total, Long staffId, String staffName, String storeName) {
		super();
		this.total = total;
		this.staffId = staffId;
		this.staffName = staffName;
		this.storeName = storeName;
	}
	public Double getTotal() {
		return total;
	}
	public Long getStaffId() {
		return staffId;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getStoreName() {
		return storeName;
	}
	@Override
	public String toString() {
		return "TradeRankingStaffDTO [total=" + total + ", staffId=" + staffId + ", staffName=" + staffName
				+ ", storeName=" + storeName + "]";
	}
	
//	private User user;
//	private House houseData;
	
}
