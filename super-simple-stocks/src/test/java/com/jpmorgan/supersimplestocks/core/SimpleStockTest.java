package com.jpmorgan.supersimplestocks.core;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * 
 */

/**
 * @author sik
 *
 */
public class SimpleStockTest {

	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Test
	public void createValidSimpleStockTypeCOMMON() {
		SimpleStock rum = new SimpleStock.SimpleStockBuilder("RUM")
				.setType(StockType.COMMON)
				.setLastDividend(3.0D)
				.setParValue(39.0D).build();

		assertTrue(rum.getStockSymbol().equals("RUM"));
		assertTrue(rum.getType() == StockType.COMMON);
		assertTrue(rum.getLastDidvidend() == 3.0D);
		assertTrue(rum.getParValue() == 39.0D);
		
		assertTrue(rum.getDividendYieldForTickerPrice(120.0D) == 0.025D);
		assertTrue(rum.getPERatioForTickerPrice(120.0D) == 40.0D);
	}
	
	@Test
	public void createValidSimpleStockTypeCOMMONWithFixedDividend() {
		SimpleStock rum = new SimpleStock.SimpleStockBuilder("RUM")
				.setType(StockType.COMMON)
				.setFixedDividendPercent(2.0D)
				.setLastDividend(3.0D)
				.setParValue(39.0D).build();

		assertTrue(rum.getStockSymbol().equals("RUM"));
		assertTrue(rum.getType() == StockType.COMMON);
		assertTrue(rum.getFixedDividendPercent() == null);
		assertTrue(rum.getLastDidvidend() == 3.0D);
		assertTrue(rum.getParValue() == 39.0D);
	}
	
	@Test
	public void createValidSimpleStockTypePREFERRED() {
		SimpleStock rum = new SimpleStock.SimpleStockBuilder("RUM")
				.setType(StockType.PREFERRED)
				.setFixedDividendPercent(3.0D)
				.setLastDividend(3.0D)
				.setParValue(39.0D).build();

		assertTrue(rum.getStockSymbol().equals("RUM"));
		assertTrue(rum.getType() == StockType.PREFERRED);
		assertTrue(rum.getFixedDividendPercent() == 3.0D);
		assertTrue(rum.getLastDidvidend() == 3.0D);
		assertTrue(rum.getParValue() == 39.0D);
		
		assertTrue(rum.getDividendYieldForTickerPrice(120.0D) == 0.00975D);
		assertTrue(rum.getPERatioForTickerPrice(120.0D) == 40.0D);
	}

	@Test
	public void createSimpleStockWithNoParValue() {
		boolean exceptionCaught = false;
		SimpleStock rum;
		try {
			rum = new SimpleStock.SimpleStockBuilder("RUM")
					.setType(StockType.COMMON)
					.setLastDividend(3.0D).build();

			assertNotNull(rum);			
		} catch (IllegalArgumentException e) {
			exceptionCaught = true;
			assertTrue(e.getMessage().contains("Mandatory fields: null detected"));
		}
		assertTrue(exceptionCaught);

	}

	@Test
	public void createStockTypePREFERREDWithNoFixedDividend() {
		
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Field: fixedDividendPercent may not be null for StockType.PREFERRED");
		
		SimpleStock rum = new SimpleStock.SimpleStockBuilder("RUM")
					.setType(StockType.PREFERRED)
					.setLastDividend(3.0D)
					.setParValue(18.0D)
					.build();
			
			assertNotNull(rum);
	}

}
