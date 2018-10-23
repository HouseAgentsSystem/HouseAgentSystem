package com.houseAgent.trade.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.houseAgent.common.web.ExtAjaxResponse;
import com.houseAgent.common.web.ExtjsPageRequest;
import com.houseAgent.common.web.SessionUtil;
import com.houseAgent.common.web.TreeNode;
import com.houseAgent.house.domain.House;
import com.houseAgent.house.domain.HouseDTO;
import com.houseAgent.house.service.IHouseService;
import com.houseAgent.staff.domain.Staff;
import com.houseAgent.staff.service.IStaffService;
import com.houseAgent.staff.service.StaffService;
import com.houseAgent.trade.domain.Trade;
import com.houseAgent.trade.domain.TradeDTO;
import com.houseAgent.trade.service.ITradeService;
import com.houseAgent.user.domain.User;
import com.houseAgent.user.service.IUserService;

@RestController
@RequestMapping("/trade")
public class TradeController {
	
	@Autowired
	private ITradeService tradeService;
	@Autowired
	private IStaffService staffService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IHouseService houseService;
	
	@PostMapping("/addTrade")
	public ExtAjaxResponse saveTrade(@RequestParam("id") Long houseId, String userName, Double agencyFees, Double actualPrice) {
		try {
//			Trade trade = new Trade();
//			//设为已售
//			User user = userService.findUserByUserName(userName);
//			if(user != null) {
//				trade.setUser(user);
//			} else {
//				return new ExtAjaxResponse(false, "用户不存在！");
//			}
//			
//			HouseDTO dto = houseService.findOne(houseId);
//			House house = new House();
//			
//			
//			tradeService.save(trade);
			return tradeService.saveOneTrade(houseId, userName, agencyFees, actualPrice);
			//return new ExtAjaxResponse(true, "记录交易成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return new ExtAjaxResponse(false, "记录交易失败！");
		}
	}
	
	@GetMapping("/findNodes")
	public List<TreeNode> getTreeNode(@RequestParam("node") String node, String nodeId, HttpSession session) {
		if(node.equals("root")) {
			//Staff staff = SessionUtil.getStaff(session);
			Staff staff = staffService.findById(7L);
			
			return tradeService.findNodes(staff);
		} else {
			return tradeService.findNodes(Long.parseLong(nodeId));
		}
	}
	
	@GetMapping("/findTrade")
	public Page<TradeDTO> getTradePage(Long nodeId, Boolean isLeaf, ExtjsPageRequest pageable, HttpSession session) {
		if(nodeId != null) {
			if(isLeaf) {
				return tradeService.findTradeByStaffId(nodeId, pageable.getPageable());
			} else {
				return tradeService.findTradeByStoreId(nodeId, pageable.getPageable());
			}
		} else {
			//Staff staff = SessionUtil.getStaff(session);
			Staff staff = staffService.findById(7L);
			return tradeService.findTradeAllByStaff(staff, pageable.getPageable());
		}
	}
}
