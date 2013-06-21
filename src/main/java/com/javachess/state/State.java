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
	 * @param move
	 * @param boar
	 * @param context
	 * @return
	 */
	public boolean isValidMove(Move move, Board board, StateContext context);
	
	/**
	 * 
	 * @param lastMove
	 * @param board
	 * @return
	 */
	public void executeTransition(StateContext context, Board board);
	
	/**
	 * 
	 * @return
	 */
	public boolean isEnded();
}
