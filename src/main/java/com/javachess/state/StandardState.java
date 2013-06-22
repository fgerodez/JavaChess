package com.javachess.state;

import static com.javachess.evaluator.BoardEvaluator.isCheck;
import static com.javachess.evaluator.BoardEvaluator.isCheckMate;
import static com.javachess.evaluator.BoardEvaluator.isStaleMate;

import com.javachess.board.Board;
import com.javachess.move.Move;

public class StandardState implements BoardState {

	@Override
	public void executeTransition(Move move, Board board, StateContext context) {

		if (isCheck(context.currentColor(), board))
			context.setCurrentState(new CheckState());

		if (isCheckMate(context.currentColor(), context.getCtxMoves(), board))
			context.setCurrentState(new CheckMateState());

		if (isStaleMate(context.currentColor(), context.getCtxMoves(), board))
			context.setCurrentState(new StaleMateState());
	}
}
