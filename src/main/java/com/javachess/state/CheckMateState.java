package com.javachess.state;

import com.javachess.board.Board;
import com.javachess.move.Move;

public class CheckMateState implements BoardState {

	@Override
	public void executeTransition(Move move, Board board, StateContext context) {
		throw new IllegalStateException("Can't transition from CheckMate!");
	}
}
