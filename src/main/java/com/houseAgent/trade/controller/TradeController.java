package com.houseAgent.trade.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.houseAgent.common.web.ExtjsPageRequest;
import com.houseAgent.common.web.SessionUtil;
import com.houseAgent.common.web.TreeNode;
import com.houseAgent.staff.domain.Staff;
import com.houseAgent.staff.service.IStaffService;
import com.houseAgent.staff.service.StaffService;
import com.houseAgent.trade.domain.TradeDTO;
import com.houseAgent.trade.service.ITradeService;

@RestController
@RequestMapping("/trade")
public class TradeController {
	
	@Autowired
	private ITradeService tradeService;
	@Autowired
	private IStaffService staffService;
	
	@GetMapping("/findNodes")
	public List<TreeNode> getTreeNode(@RequestParam("node") String node, String nodeId, HttpSession session) {
		if(node.equals("root")) {
			//Staff staff = SessionUtil.getStaff(session);
			Staff staff = staffService.findOne(1L);
			
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
			Staff staff = staffService.findOne(1L);
			return tradeService.findTradeAllByStaff(staff, pageable.getPageable());
		}
	}
}
