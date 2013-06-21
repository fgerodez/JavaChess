package com.javachess.state;

import java.util.List;

import com.javachess.board.Board;
import com.javachess.generator.MoveGenerator;
import com.javachess.move.Move;
import com.javachess.piece.Color;

public class FirstMoveState implements State {

	MoveGenerator generator;

	@Override
	public boolean isValidMove(Move move, Board board, StateContext context) {
		Move lastMove = context.lastMove();
		List<Move> legalMoves = generator.generateMoves(context.currentColor(), lastMove, board);

		return legalMoves.contains(move);
	}

	@Override
	public void executeTransition(StateContext context, Board board) {

		if (context.currentColor().equals(Color.WHITE))
			context.setCurrentState(new StandardState());
	}

	@Override
	public boolean isEnded() {
		return false;
	}
}
