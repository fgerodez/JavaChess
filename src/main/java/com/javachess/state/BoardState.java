package com.javachess.state;

import com.javachess.board.Board;
import com.javachess.game.StateContext;
import com.javachess.move.Move;

/**
 * 
 * @author francois
 *
 */
public interface BoardState {
	
	/**
	 * 
	 * @param lastMove
	 * @param board
	 * @return
	 */
	public BoardState executeTransition(Move move, Board board, StateContext context);
}
