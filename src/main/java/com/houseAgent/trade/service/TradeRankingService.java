package com.houseAgent.trade.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.houseAgent.trade.domain.TradeRankingStaffDTO;
import com.houseAgent.trade.domain.TradeRankingStoreDTO;
import com.houseAgent.trade.repository.TradeRepository;

@Service
public class TradeRankingService implements ITradeRankingService {
	@Autowired
	TradeRepository tradeRepository;
	Date startTime;
	Date endTime;
	@Override
	/**
	 * 管理员 查询所有员工营业额排名
	 * @param month
	 * @return
	 */
	public List<TradeRankingStaffDTO> totalGroupByAllStaff(Integer month) {
		// TODO Auto-generated method stub
	    this.monthToDate(month);
		return tradeRepository.totalGroupByAllStaff(startTime, endTime);
	}
	/**
	 * 管理员 查询门店营业额排名
	 * @param month
	 * @return
	 */
	@Override
	public List<TradeRankingStoreDTO> totalGroupByStore(Integer month) {
	    this.monthToDate(month);
		return tradeRepository.totalGroupByStore(startTime, endTime);
		
	}
	/**
	 * 经理 查询本门店下员工营业额排名
	 * @param session
	 * @param month
	 * @return
	 */
	@Override
	public List<TradeRankingStaffDTO> totalGroupByStaffByStore(Integer month, Long storeId) {
	    this.monthToDate(month);
		return tradeRepository.totalGroupByStaffByStore(startTime, endTime, storeId);
	}
	public void monthToDate(Integer month) {
		if(month == null) {
	    	month = 0;
	    }else {
	    	month--;
	    }
	    GregorianCalendar gc = new GregorianCalendar();
//	    gc.set(Calendar.YEAR,2018);//设置年
	    gc.set(Calendar.MONTH, month.intValue());//这里0是1月..以此向后推，这个月
	    gc.set(Calendar.DAY_OF_MONTH, 1);		//设置天
	    gc.set(Calendar.HOUR_OF_DAY, 0);
	    gc.set(Calendar.MINUTE, 0);
	    gc.set(Calendar.SECOND, 0);
	    startTime = gc.getTime();
	    gc.set(Calendar.MONTH, month.intValue()+1);	//下个月
	    endTime = gc.getTime();
	    System.out.println("startTime:"+startTime);
	    System.out.println("endTime:"+endTime);
	}
}
