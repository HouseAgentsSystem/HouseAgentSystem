package com.houseAgent.trade.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.houseAgent.common.web.SessionUtil;
import com.houseAgent.trade.domain.TradeRankingStaffDTO;
import com.houseAgent.trade.domain.TradeRankingStoreDTO;
import com.houseAgent.trade.service.TradeRankingService;

@RestController
public class TradeRankingController {
	
	@Autowired
	TradeRankingService tradeRankingService;
	
	/**
	 * 管理员 查询门店营业额排名
	 * @param month
	 * @return
	 */
	@RequestMapping("/admin/tradeRanking/store")
	public List<TradeRankingStoreDTO> findStoreRanking(Integer month) {
		return tradeRankingService.totalGroupByStore(month);
	}
	/**
	 * 管理员 查询所有员工营业额排名,
	 * 经理 查询本门店下员工营业额排名
	 * 管理员或者经理
	 * @param session
	 * @param month
	 * @return
	 */
	@RequestMapping("/tradeRanking/staff")
	public List<TradeRankingStaffDTO> findStaffRankingByStoreId(HttpSession session, Integer month, Long storeId) {
//		SessionUtil.setGroupNames(session, "admin");
		
		if(SessionUtil.getGroupNames(session).equals("管理员")) {
			if(storeId==null||storeId==0L) {
				List<TradeRankingStaffDTO> tradeRankingStaffDTOs = tradeRankingService.totalGroupByAllStaff(month);
				int length = tradeRankingStaffDTOs.size();
				if(length>10) length = 10;
				return tradeRankingStaffDTOs.subList(0, length);	//返回前10条记录，可修改
			}
		}else {
			storeId = SessionUtil.getStaff(session).getStore().getId();
			if(storeId==null)storeId = 1L;
		}
		List<TradeRankingStaffDTO> tradeRankingStaffDTOs = tradeRankingService.totalGroupByStaffByStore(month, storeId);
		int length = tradeRankingStaffDTOs.size();
		if(length>10) length = 10;
		return tradeRankingStaffDTOs.subList(0, length);
	}
}
