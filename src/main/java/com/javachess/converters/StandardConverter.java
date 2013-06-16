package com.javachess.converters;

import com.javachess.boards.Square;
import com.javachess.exceptions.ConversionException;

public class StandardConverter implements NotationConverter {

	private enum Letter {
		A, B, C, D, E, F, G, H
	}

	@Override
	public Square getSrc(String notation) throws ConversionException {
		return toSquare(notation.substring(0, 2));
	}

	@Override
	public Square getDst(String notation) throws ConversionException {
		return toSquare(notation.substring(2, 4));
	}
	
	private Square toSquare(String code) throws ConversionException {
		checkLength(code);

		Square square = null;

		try {
			Letter letter = Letter.valueOf(code.substring(0, 1));
			int row = Integer.parseInt(code.substring(1, 2));

			checkRow(row);

			square = new Square(letter.ordinal(), row - 1);
		} catch (Exception e) {
			throw new ConversionException("An error occured while converting " + code + " " + e.getMessage());
		}

		return square;
	}
	
	public String toCode(Square square) throws ConversionException {
		if (square == null)
			return "";

		if (!square.isValid())
			throw new ConversionException("Invalid square");

		return Letter.values()[square.getCol()].toString() + (square.getRow() + 1);
	}

	private void checkRow(int row) throws ConversionException {
		if (row < 1 || row > 8)
			throw new ConversionException("Row value must be between 1 and 8");
	}

	private void checkLength(String code) throws ConversionException {
		if (code == null || code.length() != 2)
			throw new ConversionException("Code length must be exactly 2 characters");
	}
}
