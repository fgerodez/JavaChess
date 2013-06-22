package com.javachess.state;

import com.javachess.board.Board;
import com.javachess.move.Move;

public class StaleMateState implements State {

	@Override
	public void executeTransition(Move move, Board board, StateContext context) {
		throw new IllegalStateException("Can't transition from StaleMate!");
	}
}
