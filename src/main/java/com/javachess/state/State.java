package com.javachess.state;

import com.javachess.board.Board;
import com.javachess.move.Move;

/**
 * 
 * @author francois
 *
 */
public interface State {
	
	/**
	 * 
	 * @param lastMove
	 * @param board
	 * @return
	 */
	public void executeTransition(Move move, Board board, StateContext context);
}
