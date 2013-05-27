package com.javachess.helpers.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.javachess.board.Square;
import com.javachess.exceptions.ConversionException;
import com.javachess.helpers.Utils;

public class UtilsTests {

	@Test
	public void chessValidCodeConversion() throws ConversionException {
		Square square = Utils.toSquare("A8");

		assertEquals(0, square.getCol());
		assertEquals(7, square.getRow());

		square = Utils.toSquare("B6");

		assertEquals(1, square.getCol());
		assertEquals(5, square.getRow());
	}

	@Test
	public void chessInvalidCodeConversion() throws ConversionException {
		try {
			Utils.toSquare("ZZZ");
			fail("3 characters code didn't fail");
		} catch (ConversionException ex) {}

		try {
			Utils.toSquare("11");
			fail("figure as column letter didn't fail");
		} catch (ConversionException ex) {}

		try {
			Utils.toSquare("Z6");
			fail("column letter beyond H didn't fail");
		} catch (ConversionException ex) {}

		try {
			Utils.toSquare("A9");
			fail("row number greater than 8 didn't fail");
		} catch (ConversionException ex) {}

		try {
			Utils.toSquare("A0");
			fail("row number smaller than 1 didn't fail");
		} catch (ConversionException ex) {}
	}

	@Test
	public void chessValidSquareConversion() throws ConversionException {
		String code = Utils.toCode(new Square(0, 0));
		assertEquals("A1", code);

		code = Utils.toCode(new Square(5, 4));
		assertEquals("F5", code);
		
		code = Utils.toCode(null);
		assertEquals("", code);
	}
	
	@Test
	public void chessInvalidSquareConversion() throws ConversionException {
		try {
			Utils.toCode(new Square(-1, 5));
			fail("col number smaller than 0 didn't fail");
		} catch (ConversionException ex) {}
		
		try {
			Utils.toCode(new Square(8, 5));
			fail("col number greater than 7 didn't fail");
		} catch (ConversionException ex) {}
		
		try {
			Utils.toCode(new Square(3, -1));
			fail("row number smaller than 0 didn't fail");
		} catch (ConversionException ex) {}
		
		try {
			Utils.toCode(new Square(3, 8));
			fail("row number greater than 7 didn't fail");
		} catch (ConversionException ex) {}
	}
}
