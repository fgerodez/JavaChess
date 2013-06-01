package com.javachess.players;

import com.javachess.board.Color;

/**
 * Chess player contract
 * 
 * @author Ouzned
 *
 */
public interface Player {

	/**
	 * Prompts the user for the next move. The value returned must be in a chess
	 * notation that can be understood by the board's NotationConverter
	 * 
	 * @return the next move encoded in a given chess notation
	 */
	public String takeAction();

	/**
	 * @return the player's color
	 */
	public Color getColor();
}
