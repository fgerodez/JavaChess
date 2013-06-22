package com.javachess.state;

import com.javachess.board.Board;
import com.javachess.evaluator.BoardEvaluator;
import com.javachess.move.Move;

public class StandardState implements State {

	BoardEvaluator evaluator;

	@Override
	public void executeTransition(Move move, Board board, StateContext context) {

		if (evaluator.isCheck(context.currentColor(), board))
			context.setCurrentState(new CheckState());

		if (evaluator.isCheckMate(context.currentColor(), context.getCtxMoves(), board))
			context.setCurrentState(new CheckMateState());

		if (evaluator.isStaleMate(context.currentColor(), context.getCtxMoves(), board))
			context.setCurrentState(new StaleMateState());
	}
}
