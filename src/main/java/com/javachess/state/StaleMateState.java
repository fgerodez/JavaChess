package com.javachess.state;

import com.javachess.board.Board;
import com.javachess.move.Move;

public class StaleMateState implements State {

	@Override
	public boolean isValidMove(Move move, Board board, StateContext context) {
		return false;
	}

	@Override
	public void executeTransition(StateContext context, Board board) {
		throw new IllegalStateException("Can't transition from StaleMate!");
	}

	@Override
	public boolean isEnded() {
		return true;
	}
}
