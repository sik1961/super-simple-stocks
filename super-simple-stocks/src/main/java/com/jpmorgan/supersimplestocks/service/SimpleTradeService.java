/**
 * 
 */
package com.jpmorgan.supersimplestocks.service;

import java.util.ArrayList;
import java.util.Collection;

import com.jpmorgan.supersimplestocks.core.SimpleTrade;
import com.jpmorgan.supersimplestocks.core.TradeService;

/**
 * @author sik
 *
 */
public class SimpleTradeService implements TradeService {

	private Collection<SimpleTrade> trades;
	
	public SimpleTradeService() {
		super();
		this.trades = new ArrayList<SimpleTrade>();
	}
	
	/**
	 * @return
	 */
	public Collection<SimpleTrade> getAllTrades() {
		return this.trades;
	}

	/**
	 * @param epochTime
	 * @return
	 */
	public Collection<SimpleTrade> getTradesSince(final long epochTime) {
		Collection<SimpleTrade> retVal = new ArrayList<SimpleTrade>();
		for (SimpleTrade trade: this.trades) {
			if (trade.getTimestamp() > epochTime) {
				retVal.add(trade);
			}
		}
		return retVal;
	}

	/**
	 * @param trade
	 */
	public void addTrade(final SimpleTrade trade) {
		this.trades.add(trade);
		
	}

}
