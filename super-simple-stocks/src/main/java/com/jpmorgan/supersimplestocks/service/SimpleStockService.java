/**
 * 
 */
package com.jpmorgan.supersimplestocks.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.jpmorgan.supersimplestocks.core.SimpleStock;
import com.jpmorgan.supersimplestocks.core.StockService;
import com.jpmorgan.supersimplestocks.core.StockType;

/**
 * @author sik
 *
 */
public class SimpleStockService implements StockService {
	private Collection<SimpleStock> stockList;
	private StockCalculator calculator = new StockCalculator();
	
	public SimpleStockService() {
		super();
		this.stockList = this.initStock();
	}

	/**
	 * Return stock list
	 */
	public Collection<SimpleStock> getStockList() {
		return this.stockList;
	}
	
	/**
	 * Return a stock by symbol
	 */
	public SimpleStock getStockBySymbol(String symbol) {
		for (SimpleStock stock:this.stockList) {
			if (stock.getStockSymbol().equals(symbol)) {
				return stock;
			}
		}
		return null;
	}
	
	/**
	 * Add stock to list
	 * @param stock
	 * @return
	 */
	public String addStockList(SimpleStock stock) {
		if (this.getStockBySymbol(stock.getStockSymbol()) != null) {
			throw new IllegalStateException("Stock symbol already exists: " + stock.getStockSymbol());
		}
		this.stockList.add(stock);
		return this.getStockBySymbol(stock.getStockSymbol()).getStockSymbol();
	}

	/**
	 * Remove Stock from list
	 * @param symbol
	 */
	public void removeStockList(String symbol) {
		if (this.getStockBySymbol(symbol) == null) {
			throw new IllegalStateException("Stock not found with symbol: " + symbol);
		}
		SimpleStock toRemove = this.getStockBySymbol(symbol);
		this.stockList.remove(toRemove);
	}
	
	public double getGeometricMeanForStocks(final Collection<SimpleStock> stocks) {
		List<Double> prices = new ArrayList<Double>();   
		for (SimpleStock stock: stocks) {
			prices.add(stock.getParValue());
		}
		return calculator.getGeometricMean(prices);
		
	}
	
	/**
	 * Initialise stock list
	 * @return
	 */
	private Collection<SimpleStock> initStock() {
		Collection<SimpleStock> retVal = new ArrayList<SimpleStock>();
		retVal.add(new SimpleStock.SimpleStockBuilder("TEA")
			.setType(StockType.COMMON)
			.setLastDividend(0.0D)
			.setParValue(100.0D)
			.build());
		retVal.add(new SimpleStock.SimpleStockBuilder("POP")
			.setType(StockType.COMMON)
			.setLastDividend(8.0D)
			.setParValue(100.0D)
			.build());
		retVal.add(new SimpleStock.SimpleStockBuilder("ALE")
			.setType(StockType.COMMON)
			.setLastDividend(23.0D)
			.setParValue(60.0D)
			.build());
		retVal.add(new SimpleStock.SimpleStockBuilder("GIN")
			.setType(StockType.PREFERRED)
			.setLastDividend(8.0D)
			.setFixedDividendPercent(2.0D)
			.setParValue(100.0D)
			.build());
		retVal.add(new SimpleStock.SimpleStockBuilder("JOE")
			.setType(StockType.COMMON)
			.setLastDividend(13.0D)
			.setParValue(250.0D)
			.build());
		return retVal;
	}
}
