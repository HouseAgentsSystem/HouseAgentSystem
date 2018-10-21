package com.houseAgent.trade.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.houseAgent.trade.domain.Trade;
import com.houseAgent.trade.domain.TradeReportDTO;
import com.houseAgent.trade.repository.TradeRepository;
@Service
public class TradeReportService implements ITradeReportService {

	@Autowired
	TradeRepository tradeRepository;
	@Override
	public List<TradeReportDTO> findAll(Long storeId) {
		// TODO Auto-generated method stub
		List<Trade> trades = null;;
		if(storeId==null) {
			trades = (List<Trade>) tradeRepository.findAll();
		}else {
			System.out.println(storeId);
			trades = (List<Trade>) tradeRepository.findTradeByStoreId(storeId);
			System.out.println(trades);
		}
		if(trades!=null) {
			List<TradeReportDTO> tradeReportDTOs = new ArrayList<TradeReportDTO>();
			for (Trade trade : trades) {
				TradeReportDTO tradeReportDTO = new TradeReportDTO();
				TradeReportDTO.entityToDto(trade, tradeReportDTO);
				tradeReportDTOs.add(tradeReportDTO);
			}
			return tradeReportDTOs;
		}
		return null;
	}
}
