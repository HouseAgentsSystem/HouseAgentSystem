package com.houseAgent.trade.service;

import java.util.Date;
import java.util.List;

import com.houseAgent.trade.domain.TradeRankingStaffDTO;
import com.houseAgent.trade.domain.TradeRankingStoreDTO;

public interface ITradeRankingService {
	
	public List<TradeRankingStaffDTO> totalGroupByAllStaff(Integer month);
	public List<TradeRankingStoreDTO> totalGroupByStore(Integer month);
	public List<TradeRankingStaffDTO> totalGroupByStaffByStore(Integer month, Long storeId);
}
