package com.houseAgent.trade.domain;

public class TradeRankingStoreDTO {
	
	private Double total;		//该月销售额
	private Long storeId;		//门店id
	private String storeName;	//门店名
	
	public TradeRankingStoreDTO(Double total, Long storeId, String storeName) {
		super();
		this.total = total;
		this.storeId = storeId;
		this.storeName = storeName;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	@Override
	public String toString() {
		return "TradeRankingStoreDTO [total=" + total + ", storeId=" + storeId + ", storeName=" + storeName
				+ "]";
	}
	
}
