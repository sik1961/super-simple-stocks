/**
 * 
 */
package com.jpmorgan.supersimplestocks.core;

/**
 * @author sik
 *
 */
public class SimpleTrade {
	private long timestamp;
	private TradeType tradeType;
	private SimpleStock stock;
	private int number;
	
	/**
	 * @param tradeType
	 * @param stock
	 * @param number
	 */
	public SimpleTrade(TradeType tradeType, SimpleStock stock, int number) {
		super();
		this.timestamp = System.currentTimeMillis();
		this.tradeType = tradeType;
		this.stock = stock;
		this.number = number;
	}
	
	/**
	 * @param timestamp
	 * @param tradeType
	 * @param stock
	 * @param number
	 */
	public SimpleTrade(long timestamp, TradeType tradeType, SimpleStock stock, int number) {
		super();
		this.timestamp = timestamp;
		this.tradeType = tradeType;
		this.stock = stock;
		this.number = number;
	}
	/**
	 * @return the timestamp
	 */
	public long getTimestamp() {
		return this.timestamp;
	}
	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	/**
	 * @return the tradeType
	 */
	public TradeType getTradeType() {
		return this.tradeType;
	}
	/**
	 * @param tradeType the tradeType to set
	 */
	public void setTradeType(TradeType tradeType) {
		this.tradeType = tradeType;
	}
	/**
	 * @return the stock
	 */
	public SimpleStock getStock() {
		return this.stock;
	}
	/**
	 * @param stock the stock to set
	 */
	public void setStock(SimpleStock stock) {
		this.stock = stock;
	}
	/**
	 * @return the number
	 */
	public int getNumber() {
		return this.number;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}
	/**
	 * @return
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.number;
		result = prime * result
				+ ((this.stock == null) ? 0 : this.stock.hashCode());
		result = prime * result
				+ (int) (this.timestamp ^ (this.timestamp >>> 32));
		result = prime * result
				+ ((this.tradeType == null) ? 0 : this.tradeType.hashCode());
		return result;
	}
	/**
	 * @param obj
	 * @return
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SimpleTrade other = (SimpleTrade) obj;
		if (this.number != other.number)
			return false;
		if (this.stock == null) {
			if (other.stock != null)
				return false;
		} else if (!this.stock.equals(other.stock))
			return false;
		if (this.timestamp != other.timestamp)
			return false;
		if (this.tradeType != other.tradeType)
			return false;
		return true;
	}
	/**
	 * @return
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SimpleTrade [timestamp=");
		builder.append(this.timestamp);
		builder.append(", tradeType=");
		builder.append(this.tradeType);
		builder.append(", stock=");
		builder.append(this.stock);
		builder.append(", number=");
		builder.append(this.number);
		builder.append("]");
		return builder.toString();
	}
	
	
}
