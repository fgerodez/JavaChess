package com.javachess.state;

import static com.javachess.evaluator.BoardEvaluator.isStaleMate;

import com.javachess.board.Board;
import com.javachess.move.Move;

public class CheckState implements BoardState {

	@Override
	public void executeTransition(Move move, Board board, StateContext context) {

		if (isStaleMate(context.currentColor(), context.getCtxMoves(), board))
			context.setCurrentState(new StaleMateState());

		context.setCurrentState(new StandardState());
	}
}
