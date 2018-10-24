package com.houseAgent.trade.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.houseAgent.staff.domain.Staff;
import com.houseAgent.trade.domain.Trade;
import com.houseAgent.trade.domain.TradeRankingStaffDTO;
import com.houseAgent.trade.domain.TradeRankingStoreDTO;


@Repository
public interface TradeRepository extends PagingAndSortingRepository<Trade, Long> 
							,JpaSpecificationExecutor<Trade>{
	
//	public  findByOneMonthAndStaff();
	//管理员查看所有员工个月的营业额
	//@Param("cn")   :cn
	
	@Query("select new com.houseAgent.trade.domain.TradeRankingStaffDTO(SUM(tra.actualPrice) as total,tra.houseData.staff.id, tra.houseData.staff.realName, tra.houseData.staff.store.storeName) "
			+ "from Trade as tra where tra.saleDate BETWEEN :startT and :endT  GROUP BY tra.houseData.staff.id order by total desc")
	public List<TradeRankingStaffDTO> totalGroupByAllStaff(@Param("startT") Date startTime, @Param("endT")Date endTime);
//	//管理员查看所有门店个月的营业额
	@Query("select new com.houseAgent.trade.domain.TradeRankingStoreDTO(SUM(tra.actualPrice) as total,tra.houseData.staff.store.id, tra.houseData.staff.store.storeName) "
			+ "from Trade as tra where tra.saleDate BETWEEN :startT and :endT GROUP BY tra.houseData.staff.store.id order by total desc")
	public List<TradeRankingStoreDTO> totalGroupByStore(@Param("startT") Date startTime, @Param("endT") Date endTime);
//	//门店经理查看旗下员工个月的营业额
	@Query("select new com.houseAgent.trade.domain.TradeRankingStaffDTO(SUM(tra.actualPrice) as total,tra.houseData.staff.id, tra.houseData.staff.realName, tra.houseData.staff.store.storeName) "
			+ "from Trade as tra where tra.saleDate BETWEEN :startT and :endT and tra.houseData.staff.store.id = :storeId GROUP BY tra.houseData.staff.id order by total desc")
	public List<TradeRankingStaffDTO> totalGroupByStaffByStore(@Param("startT") Date startTime, @Param("endT") Date endTime, @Param("storeId")Long storeId);
	
	@Query("from Trade trade where trade.houseData.staff.id = ?1")
	public List<Trade> findTradeByStaffId(String id);
	
	@Query("from Trade trade where trade.houseData.store.id = ?1")
	public List<Trade> findTradeByStoreId(Long id);
	
}
