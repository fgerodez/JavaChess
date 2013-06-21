package com.javachess.converter;

import com.javachess.board.Square;

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
	public Square getSrc(String notation);

	public Square getDst(String notation);
}
