/**
 * 
 */
package com.jpmorgan.supersimplestocks.core;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.jpmorgan.supersimplestocks.service.SimpleStockService;
import com.jpmorgan.supersimplestocks.service.SimpleTradeService;

/**
 * @author sik
 *
 */
public class TradeServiceTest {

	private static final long FIFTEEN_MINS_AGO = System.currentTimeMillis() - (1000L * 60 * 15);
	private static final long SIXTEEN_MINS_AGO = System.currentTimeMillis() - (1000L * 60 * 16);
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	
	SimpleTradeService tradeService = new SimpleTradeService();
	SimpleStockService stockService = new SimpleStockService();
	
	@Test
	public void addTrade() {
		
		assertTrue(tradeService.getAllTrades().size() == 0);
		
		this.tradeService.addTrade(new SimpleTrade(TradeType.BUY, this.stockService.getStockBySymbol("TEA"), 50));
		this.tradeService.addTrade(new SimpleTrade(TradeType.BUY, this.stockService.getStockBySymbol("GIN"), 15));
		this.tradeService.addTrade(new SimpleTrade(TradeType.BUY, this.stockService.getStockBySymbol("JOE"), 5));
		this.tradeService.addTrade(new SimpleTrade(TradeType.SELL, this.stockService.getStockBySymbol("TEA"), 5));
		this.tradeService.addTrade(new SimpleTrade(TradeType.BUY, this.stockService.getStockBySymbol("ALE"), 5));
		this.tradeService.addTrade(new SimpleTrade(TradeType.SELL, this.stockService.getStockBySymbol("TEA"), 5));
		this.tradeService.addTrade(new SimpleTrade(TradeType.SELL, this.stockService.getStockBySymbol("POP"), 5));
		this.tradeService.addTrade(new SimpleTrade(TradeType.BUY, this.stockService.getStockBySymbol("TEA"), 5));
		this.tradeService.addTrade(new SimpleTrade(TradeType.SELL, this.stockService.getStockBySymbol("TEA"), 5));
		this.tradeService.addTrade(new SimpleTrade(TradeType.BUY, this.stockService.getStockBySymbol("POP"), 5));
		
		assertTrue(tradeService.getAllTrades().size() == 10);
		
	}
	
	@Test
	public void addMoreTradesTestTradesSince() {
		
		assertTrue(tradeService.getAllTrades().size() == 0);
		
		this.tradeService.addTrade(new SimpleTrade(TradeType.BUY, this.stockService.getStockBySymbol("TEA"), 50));
		this.tradeService.addTrade(new SimpleTrade(TradeType.BUY, this.stockService.getStockBySymbol("GIN"), 15));
		this.tradeService.addTrade(new SimpleTrade(TradeType.BUY, this.stockService.getStockBySymbol("JOE"), 5));
		this.tradeService.addTrade(new SimpleTrade(TradeType.SELL, this.stockService.getStockBySymbol("TEA"), 5));
		this.tradeService.addTrade(new SimpleTrade(TradeType.BUY, this.stockService.getStockBySymbol("ALE"), 5));
		this.tradeService.addTrade(new SimpleTrade(TradeType.SELL, this.stockService.getStockBySymbol("TEA"), 5));
		this.tradeService.addTrade(new SimpleTrade(TradeType.SELL, this.stockService.getStockBySymbol("POP"), 5));
		this.tradeService.addTrade(new SimpleTrade(TradeType.BUY, this.stockService.getStockBySymbol("TEA"), 5));
		this.tradeService.addTrade(new SimpleTrade(TradeType.SELL, this.stockService.getStockBySymbol("TEA"), 5));
		this.tradeService.addTrade(new SimpleTrade(TradeType.BUY, this.stockService.getStockBySymbol("POP"), 5));
		
		//add older trades
		this.tradeService.addTrade(new SimpleTrade(1L,TradeType.BUY, this.stockService.getStockBySymbol("POP"), 50));
		this.tradeService.addTrade(new SimpleTrade(10L,TradeType.BUY, this.stockService.getStockBySymbol("TEA"), 50));
		this.tradeService.addTrade(new SimpleTrade(100L,TradeType.BUY, this.stockService.getStockBySymbol("ALE"), 50));
		this.tradeService.addTrade(new SimpleTrade(1000L,TradeType.BUY, this.stockService.getStockBySymbol("GIN"), 50));
		this.tradeService.addTrade(new SimpleTrade(10000L,TradeType.BUY, this.stockService.getStockBySymbol("JOE"), 50));
		this.tradeService.addTrade(new SimpleTrade(SIXTEEN_MINS_AGO,TradeType.BUY, this.stockService.getStockBySymbol("GIN"), 50));
		
		assertTrue(tradeService.getAllTrades().size() == 16);
		
		for (SimpleTrade t:tradeService.getAllTrades()) {
			System.out.println(t);
		}
		
		assertTrue(tradeService.getTradesSince(FIFTEEN_MINS_AGO).size() == 10);
		
	}
	
	
	
}
