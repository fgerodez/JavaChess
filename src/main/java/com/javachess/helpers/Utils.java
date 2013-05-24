package com.javachess.helpers;

import java.util.Collection;

import com.javachess.board.Letter;
import com.javachess.board.Square;
import com.javachess.exceptions.ConversionException;

public class Utils {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void nullSafeAdd(Collection col, Object test) {
		if (test != null)
			col.add(test);
	}

	/**
	 * Converts a chess code such as A1 - B6 to a Square object usable
	 * throughout the application model
	 * 
	 * @param code
	 *            The chess code
	 * @return The corresponding Square object
	 * @throws ConversionException
	 *             if an error occurs during the conversion
	 */
	public static Square toSquare(String code)
			throws ConversionException {
		checkLength(code);

		Square square = null;

		try {
			Letter letter = Letter.valueOf(code.substring(0, 1));
			int row = Integer.parseInt(code.substring(1, 2));

			checkRow(row);

			square = new Square(letter.ordinal() + 1, row);
		} catch (Exception e) {
			throw new ConversionException("An error occured while converting "
					+ code + " " + e.getMessage());
		}

		return square;
	}

	private static void checkRow(int row) throws ConversionException {
		if (row < 1 || row > 8)
			throw new ConversionException("Row value must be between 1 and 8");
	}

	private static void checkLength(String code) throws ConversionException {
		if (code == null || code.length() != 2)
			throw new ConversionException(
					"Code length must be exactly 2 characters");
	}

	/**
	 * Converts a Square object to its official chess code
	 * 
	 * @param square
	 *            The board square
	 * @return The corresponding chess code or an empty string if square is null
	 * @throws ConversionException
	 *             if an error occurs during the conversion
	 */
	public static String toCode(Square square)
			throws ConversionException {
		if (square == null)
			return "";

		checkSquare(square);

		return Letter.values()[square.getColumn() - 1].toString()
				+ square.getRow();
	}

	private static void checkSquare(Square square) throws ConversionException {
		if (square.getColumn() - 1 >= Letter.values().length
				|| square.getColumn() - 1 < 0)
			throw new ConversionException("Invalid square column value: "
					+ square.getColumn());
		
		if (square.getRow() < 1 || square.getRow() > 8)
			throw new ConversionException("Invalid square row value: "
					+ square.getRow());
	}
}
