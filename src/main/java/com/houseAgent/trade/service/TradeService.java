package com.houseAgent.trade.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Attr;
import com.houseAgent.common.web.ExtAjaxResponse;
import com.houseAgent.common.web.TreeNode;
import com.houseAgent.house.domain.House;
import com.houseAgent.house.repository.HouseRepository;
import com.houseAgent.houserent.repository.HouseRentRepository;
import com.houseAgent.staff.domain.Staff;
import com.houseAgent.staff.repository.StaffRepository;
import com.houseAgent.store.domain.Store;
import com.houseAgent.store.repository.StoreRepository;
import com.houseAgent.trade.domain.Trade;
import com.houseAgent.trade.domain.TradeDTO;
import com.houseAgent.trade.repository.TradeRepository;
import com.houseAgent.user.domain.User;
import com.houseAgent.user.repository.UserRepository;

@Service
@Transactional
public class TradeService implements ITradeService {

	@Autowired
	private TradeRepository tradeRepositoty;
	@Autowired
	private StaffRepository staffRepository;
	@Autowired
	private StoreRepository storeRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private HouseRepository houseRepository;
	

	@Override
	public void save(Trade trade) {
		tradeRepositoty.save(trade);
	}
	
	/**
	 * 根据门店id找员工Tree
	 * id 门店id
	 */
	@Override
	public List<TreeNode> findNodes(Long id) {
		List<TreeNode> nodeList = new ArrayList<>();
		
		List<Staff> staffs = staffRepository.findStaffByStoreId(id);
		for (Staff staff : staffs) {
			TreeNode node = new  TreeNode();
			
			node.setNodeId(staff.getId());
			node.setText(staff.getRealname());
			node.setLeaf(true);
			
			nodeList.add(node);
		}
		
		return nodeList;
	}

	/**
	 * 根据不同职位找相应的Tree
	 * id 员工id
	 */
	@Override
	public List<TreeNode> findNodes(Staff staff) {
		String position = staff.getPosition();
		List<TreeNode> nodeList = new ArrayList<>();
		
		if(position.equals("管理员")) {
			List<Store> stores = new ArrayList<>();
			stores = (List<Store>) storeRepository.findAll();
			for (Store store : stores) {
				TreeNode node = new  TreeNode();
				
				node.setNodeId(store.getId());
				node.setText(store.getStoreName());
				node.setLeaf(false);
				
				nodeList.add(node);
			}
		}
		
		if(position.equals("经理")) {
			Long storeId = staff.getStore().getId();
			nodeList = findNodes(storeId);
		}
		//员工没有子树，直接返回
		return nodeList;
	}

	@Override
	public Page<TradeDTO> findTradeByStaffId(Long id, Pageable pageable) {
		List<TradeDTO> dtos = new ArrayList<>();
		List<Trade> trades = tradeRepositoty.findTradeByStaffId(id);
		for (Trade trade : trades) {
			TradeDTO dto = new TradeDTO();
			TradeDTO.entityToDto(trade, dto);
			dtos.add(dto);
		}
		
		return new PageImpl<TradeDTO>(dtos, pageable, trades.size());
	}

	@Override
	public Page<TradeDTO> findTradeByStoreId(Long id, Pageable pageable) {
		List<TradeDTO> dtos = new ArrayList<>();
		List<Trade> trades = tradeRepositoty.findTradeByStoreId(id);
		for (Trade trade : trades) {
			TradeDTO dto = new TradeDTO();
			TradeDTO.entityToDto(trade, dto);
			dtos.add(dto);
		}
		
		return new PageImpl<TradeDTO>(dtos, pageable, trades.size());
	}

	@Override
	public Page<TradeDTO> findTradeAllByStaff(Staff staff, Pageable pageable) {
		String position = staff.getPosition();
		List<TradeDTO> dtos = new ArrayList<>();
		
		if(position.equals("管理员")) {
			
			List<Trade> trades = tradeRepositoty.findAll(pageable).getContent();
			for (Trade trade : trades) {
				TradeDTO dto = new TradeDTO();
				TradeDTO.entityToDto(trade, dto);
				dtos.add(dto);
			}
			
			return new PageImpl<TradeDTO>(dtos, pageable, trades.size());
		}
		
		if(position.equals("经理")) {
			return findTradeByStoreId(staff.getStore().getId(), pageable);
		}
		
		if(position.equals("员工")) {
			return findTradeByStaffId(staff.getId(), pageable);
		}
		
		return new PageImpl<TradeDTO>(dtos, pageable, dtos.size());
	}

	@Override
	public ExtAjaxResponse saveOneTrade(Long houseId, String userName, Double agencyFees, Double actualPrice) {
		try {
			Trade trade = new Trade();
			
			User user = userRepository.findByUserName(userName);
			if(user != null) {
				trade.setUser(user);
			} else {
				return new ExtAjaxResponse(false, "用户不存在！");
			}
			
			House house = houseRepository.findById(houseId).get();
			trade.setHouseData(house);
			//设为已售
			house.setState(2);
			houseRepository.save(house);
			
			trade.setSaleDate(new Date());
			trade.setAgencyFees(agencyFees);
			trade.setActualPrice(actualPrice);
			
			tradeRepositoty.save(trade);
		} catch(Exception e) {
			e.printStackTrace();
			return new ExtAjaxResponse(true, "记录交易失败！");
		}
		return new ExtAjaxResponse(true, "记录交易成功！");
	}
}
