package com.javachess.state;

import java.util.List;

import com.javachess.board.Board;
import com.javachess.evaluator.BoardEvaluator;
import com.javachess.generator.MoveGenerator;
import com.javachess.move.Move;

public class StandardState implements State {

	MoveGenerator generator;
	BoardEvaluator evaluator;

	@Override
	public boolean isValidMove(Move move, Board board, StateContext context) {
		Move lastMove = context.lastMove();
		List<Move> legalMoves = generator.generateMoves(context.currentColor(), lastMove, board);

		return legalMoves.contains(move);
	}

	@Override
	public void executeTransition(StateContext context, Board board) {

		if (evaluator.isCheck(context.currentColor()))
			context.setCurrentState(new CheckState());

		if (evaluator.isCheckMate(context.currentColor()))
			context.setCurrentState(new CheckMateState());

		if (evaluator.isStaleMate(context.currentColor()))
			context.setCurrentState(new StaleMateState());
	}

	@Override
	public boolean isEnded() {
		return false;
	}
}