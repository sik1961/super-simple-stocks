/**
 * 
 */
package com.jpmorgan.supersimplestocks.core;

import java.util.Collection;

/**
 * @author sik
 *
 */
public interface TradeService {
	Collection<SimpleTrade> getAllTrades();
	Collection<SimpleTrade> getTradesSince(long epochTime);
	void addTrade(SimpleTrade trade);
	
}
