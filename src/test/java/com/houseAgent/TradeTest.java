package com.houseAgent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.houseAgent.trade.service.ITradeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeTest {

	@Autowired
	private ITradeService tradeService;
	
	@Test
	public void addTradeTest() {
		tradeService.saveOneTrade(1L, "user1", 1000.0, 490.0);
	}
}
