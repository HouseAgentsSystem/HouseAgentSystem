package com.houseAgent.trade.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.houseAgent.common.web.SessionUtil;
import com.houseAgent.staff.domain.Staff;
import com.houseAgent.staff.service.IStaffService;
import com.houseAgent.trade.domain.TradeReportDTO;
import com.houseAgent.trade.service.ITradeReportService;
import com.houseAgent.trade.service.ITradeService;

@RestController
@RequestMapping("/report")
public class TradeReportController {
	@Autowired
	ITradeReportService tradeReportService;
	@Autowired
	IStaffService staffService;
	@RequestMapping("/findAll")
	public List<TradeReportDTO> findAll(HttpSession session) {
//		SessionUtil.setGroupNames(session, "manager");
//		Staff staff = staffService.findById(1L);
//		SessionUtil.setStaff(session, staff);
//		
//		System.out.println("staff" + staff);
		
		Long storeId = null;
		if(SessionUtil.getGroupNames(session).equals("经理")) {
			storeId = SessionUtil.getStaff(session).getStore().getId();
			System.out.println(storeId);
		}
		return tradeReportService.findAll(storeId);
	}
}
