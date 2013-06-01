package com.javachess.converters;

import com.javachess.board.Square;
import com.javachess.exceptions.ConversionException;

/**
 * This interface is used to encode/decode a specific chess notation to/from a
 * notation agnostic Square representation
 * 
 * @author Ouzned
 * 
 */
public interface NotationConverter {

	/**
	 * Converts a chess code with a given notation to a Square object usable
	 * throughout the application
	 * 
	 * @param code
	 *            The chess code in a given notation
	 * @return The corresponding Square object
	 * @throws ConversionException
	 *             if an error occurs during the conversion
	 */
	public Square toSquare(String notation) throws ConversionException;

	/**
	 * Converts a Square object to its associated notation string
	 * 
	 * @param square
	 *            The board square
	 * @return The corresponding chess notation or an empty string if square is
	 *         null
	 * @throws ConversionException
	 *             if an error occurs during the conversion
	 */
	public String toCode(Square square) throws ConversionException;
}
