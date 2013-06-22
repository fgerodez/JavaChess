package com.javachess.state.board;

import com.javachess.board.Board;
import com.javachess.game.StateContext;
import com.javachess.move.Move;
import com.javachess.state.BoardState;

public class CheckMateState implements BoardState {

	@Override
	public BoardState executeTransition(Move move, Board board, StateContext context) {
		throw new IllegalStateException("Can't transition from CheckMate!");
	}
}
