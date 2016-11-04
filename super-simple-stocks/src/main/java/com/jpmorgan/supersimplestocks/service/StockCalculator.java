/**
 * 
 */
package com.jpmorgan.supersimplestocks.service;

import java.util.List;

/**
 * @author sik
 *
 */
public class StockCalculator {
	
	public double getGeometricMean(final List<Double> data)  
	{
		double sum = data.get(0);
		for (int i = 1; i < data.size(); i++) {
			sum *= data.get(i); 
		}
		return Math.pow(sum, 1.0 / data.size()); 
	}
	
}
