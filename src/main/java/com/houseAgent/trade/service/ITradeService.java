package com.houseAgent.trade.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.houseAgent.common.web.TreeNode;
import com.houseAgent.staff.domain.Staff;
import com.houseAgent.trade.domain.TradeDTO;

public interface ITradeService {
	
	public List<TreeNode> findNodes(Long id);
	public List<TreeNode> findNodes(Staff staff);

	public Page<TradeDTO> findTradeByStaffId(Long id, Pageable pageable);
	public Page<TradeDTO> findTradeByStoreId(Long id, Pageable pageable);
	public Page<TradeDTO> findTradeAllByStaff(Staff staff, Pageable pageable);
}
