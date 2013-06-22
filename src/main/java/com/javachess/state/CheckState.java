package com.javachess.state;

import com.javachess.board.Board;
import com.javachess.evaluator.BoardEvaluator;
import com.javachess.move.Move;

public class CheckState implements State {

	BoardEvaluator evaluator;

	@Override
	public void executeTransition(Move move, Board board, StateContext context) {

		if (evaluator.isStaleMate(context.currentColor(), context.getCtxMoves(), board))
			context.setCurrentState(new StaleMateState());

		context.setCurrentState(new StandardState());
	}
}
