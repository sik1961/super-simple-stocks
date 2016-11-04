/**
 * 
 */
package com.jpmorgan.supersimplestocks.core;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.jpmorgan.supersimplestocks.service.SimpleStockService;

/**
 * @author sik
 *
 */
public class StockServiceTest {

	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	SimpleStockService stockService = new SimpleStockService();
	
	@Test
	public void addStock() {
		
		int noOfStocks =  stockService.getStockList().size();
		
		SimpleStock wkd = new SimpleStock.SimpleStockBuilder("WKD")
			.setType(StockType.COMMON)
			.setLastDividend(19.0D)
			.setParValue(178.0D)
			.build();
		
		assertTrue("WKD" == this.stockService.addStockList(wkd));
		
		assertNotNull(this.stockService.getStockBySymbol("WKD"));
		
		assertTrue(stockService.getStockList().size() == (noOfStocks + 1));
		
	}
	
	@Test
	public void addDuplicateStock() {
		
		SimpleStock tea = new SimpleStock.SimpleStockBuilder("TEA")
			.setType(StockType.COMMON)
			.setLastDividend(19.0D)
			.setParValue(178.0D)
			.build();
		
		exception.expect(IllegalStateException.class);
		exception.expectMessage("Stock symbol already exists:");
		
		this.stockService.addStockList(tea);

		
	}
	
	@Test
	public void removeStock() {
		
		int noOfStocks =  stockService.getStockList().size();
		
		this.stockService.removeStockList("TEA");
		
		assertTrue(stockService.getStockList().size() == (noOfStocks - 1));
		
		assertEquals("TEA:",null,this.stockService.getStockBySymbol("TEA"));
		
	}
	
	@Test
	public void removeNonExistantStock() {
		
		int noOfStocks =  stockService.getStockList().size();
		
		exception.expect(IllegalStateException.class);
		exception.expectMessage("Stock not found with symbol:");
		
		this.stockService.removeStockList("XXX");
		
		assertTrue(stockService.getStockList().size() == (noOfStocks - 1));
		
		assertEquals("TEA:",null,this.stockService.getStockBySymbol("TEA"));
		
	}

	@Test
	public void geometricMeanOfAllStocks() {
		assertTrue(this.stockService.getGeometricMeanForStocks(this.stockService.getStockList()) == 108.44717711976989D);
	}
	
}
