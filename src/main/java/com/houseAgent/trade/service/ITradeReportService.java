package com.houseAgent.trade.service;

import java.util.List;

import com.houseAgent.trade.domain.TradeReportDTO;

public interface ITradeReportService {

	public List<TradeReportDTO> findAll(Long storeId);
}
