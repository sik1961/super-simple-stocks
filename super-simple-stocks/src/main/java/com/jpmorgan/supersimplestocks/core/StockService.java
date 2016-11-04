/**
 * 
 */
package com.jpmorgan.supersimplestocks.core;

import java.util.Collection;

/**
 * @author sik
 *
 */
public interface StockService {
	
	Collection<SimpleStock> getStockList();
	
	SimpleStock getStockBySymbol(String symbol);
	
	String addStockList(SimpleStock stock);
	
	void removeStockList(String symbol);
		
	double getGeometricMeanForStocks(final Collection<SimpleStock> stocks);
}
