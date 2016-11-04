/**
 * 
 */
package com.jpmorgan.supersimplestocks.core;

/**
 * @author sik
 *
 */
public interface Stock {
	String getStockSymbol();

	StockType getType();
	
	Double getLastDidvidend();

	Double getFixedDividendPercent();

	Double getParValue();

	Double getDividendYieldForTickerPrice(Double price);

	Double getPERatioForTickerPrice(Double price);

}
