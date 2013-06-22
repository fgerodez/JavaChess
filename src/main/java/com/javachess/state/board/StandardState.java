package com.javachess.state.board;

import static com.javachess.evaluator.BoardEvaluator.isCheckMate;
import static com.javachess.evaluator.BoardEvaluator.isStaleMate;

import com.javachess.board.Board;
import com.javachess.game.StateContext;
import com.javachess.move.Move;
import com.javachess.state.BoardState;

public class StandardState implements BoardState {

	@Override
	public BoardState executeTransition(Move move, Board board, StateContext context) {

		if (isCheckMate(context.currentColor(), context.getCtxMoves(), board))
			return new CheckMateState();

		if (isStaleMate(context.currentColor(), context.getCtxMoves(), board))
			return new StaleMateState();
		
		return this;
	}
}
