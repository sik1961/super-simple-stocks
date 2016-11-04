/**
 * 
 */
package com.jpmorgan.supersimplestocks.core;

/**
 * @author sik
 *
 */
public enum StockType {
	COMMON("Common"),
	PREFERRED("Preferred");
	
	private String value;
	
	private StockType(final String value) {
		this.value = value;
	}
	/**
	 * Return the plain text value
	 * @return
	 */
	public String getValue() {
		return this.value;
	}
}
