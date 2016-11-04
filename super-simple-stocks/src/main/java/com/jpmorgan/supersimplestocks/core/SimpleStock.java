/**
 * 
 */
package com.jpmorgan.supersimplestocks.core;

import org.apache.log4j.Logger;

/**
 * @author sik
 *
 */
public class SimpleStock implements Stock {
	private static final int CENTO = 100;
	private static final Logger LOG = Logger.getLogger(SimpleStock.class);

	private String stockSymbol;
	private StockType type;
	private Double lastDividend;
	private Double fixedDividendPercent;
	private Double parValue;

	/**
	 * @param builder
	 */
	private SimpleStock(SimpleStockBuilder builder) {
		super();
		this.stockSymbol = builder.stockSymbol;
		this.type = builder.type;
		this.lastDividend = builder.lastDividend;
		this.fixedDividendPercent = builder.type == StockType.PREFERRED ? builder.fixedDividendPercent
				: null;
		this.parValue = builder.parValue;
	}

	/**
	 * @return the stockSymbol
	 */
	public String getStockSymbol() {
		return this.stockSymbol;
	}

	/**
	 * @return the type
	 */
	public StockType getType() {
		return this.type;
	}

	/**
	 * @return the lastDidvidend
	 */
	public Double getLastDidvidend() {
		return this.lastDividend;
	}

	/**
	 * @return the fixedDividendPercent
	 */
	public Double getFixedDividendPercent() {
		return this.fixedDividendPercent;
	}

	/**
	 * @return the parValue
	 */
	public Double getParValue() {
		return this.parValue;
	}

	/**
	 * Return dividend yield for price
	 * 
	 * @param price
	 */
	public Double getDividendYieldForTickerPrice(final Double price) {
		Double retVal = 0.0D;
		switch (this.type) {
		case COMMON:
			retVal = this.lastDividend / price;
			break;
		case PREFERRED:
			retVal = (this.parValue * (this.fixedDividendPercent / CENTO))
					/ price;
			break;
		}
		return retVal;
	}

	/**
	 * Return PE Ratio for price
	 * 
	 * @param price
	 */
	public Double getPERatioForTickerPrice(final Double price) {
		return price / this.lastDividend;
	}

	public static class SimpleStockBuilder {
		private String stockSymbol;
		private StockType type;
		private Double lastDividend;
		private Double fixedDividendPercent;
		private Double parValue;

		/**
		 * 
		 * @param stockSymbol
		 */
		public SimpleStockBuilder(final String stockSymbol) {
			this.stockSymbol = stockSymbol;
		}

		/**
		 * @param type
		 *            the type to set
		 * @return
		 */
		public SimpleStockBuilder setType(final StockType type) {
			this.type = type;
			return this;
		}

		/**
		 * @param lastDidvidend
		 *            the lastDidvidend to set
		 * @return
		 */
		public SimpleStockBuilder setLastDividend(final Double lastDividend) {
			this.lastDividend = lastDividend;
			return this;
		}

		/**
		 * @param fixedDividendPercent
		 *            the fixedDividendPercent to set
		 * @return
		 */
		public SimpleStockBuilder setFixedDividendPercent(
				final Double fixedDividendPercent) {
			this.fixedDividendPercent = fixedDividendPercent;
			return this;
		}

		/**
		 * @param parValue
		 * @return
		 */
		public SimpleStockBuilder setParValue(final Double parValue) {
			this.parValue = parValue;
			return this;
		}

		public SimpleStock build() {
			if (this.stockSymbol == null || this.type == null
					|| this.lastDividend == null || this.lastDividend < 0.0D
					|| this.parValue == null || this.parValue < 0.0D) {
				throw new IllegalArgumentException("Mandatory fields: null detected");
			} else {
				switch (this.type) {
				case COMMON:
					if (this.fixedDividendPercent != null) {
						this.fixedDividendPercent = null;
						if (LOG.isDebugEnabled()) {
							LOG.debug("StockType.COMMON = fixedDividendPercent set to: null");
						}
					}
					break;
				case PREFERRED:
					if (this.fixedDividendPercent == null) {
						throw new IllegalArgumentException(
								"Field: fixedDividendPercent may not be null for StockType.PREFERRED");
					}
					break;
				}
			}

			return new SimpleStock(this);

		}

	}

	/**
	 * @return
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(this.fixedDividendPercent);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(this.lastDividend);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(this.parValue);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime
				* result
				+ ((this.stockSymbol == null) ? 0 : this.stockSymbol.hashCode());
		result = prime * result
				+ ((this.type == null) ? 0 : this.type.hashCode());
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
		SimpleStock other = (SimpleStock) obj;
		if (Double.doubleToLongBits(this.fixedDividendPercent) != Double
				.doubleToLongBits(other.fixedDividendPercent))
			return false;
		if (Double.doubleToLongBits(this.lastDividend) != Double
				.doubleToLongBits(other.lastDividend))
			return false;
		if (Double.doubleToLongBits(this.parValue) != Double
				.doubleToLongBits(other.parValue))
			return false;
		if (this.stockSymbol == null) {
			if (other.stockSymbol != null)
				return false;
		} else if (!this.stockSymbol.equals(other.stockSymbol))
			return false;
		if (this.type != other.type)
			return false;
		return true;
	}

	/**
	 * @return
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Stock [stockSymbol=");
		builder.append(this.stockSymbol);
		builder.append(", type=");
		builder.append(this.type);
		builder.append(", lastDidvidend=");
		builder.append(this.lastDividend);
		builder.append(", fixedDividendPercent=");
		builder.append(this.fixedDividendPercent);
		builder.append(", parValue=");
		builder.append(this.parValue);
		builder.append("]");
		return builder.toString();
	}
}
