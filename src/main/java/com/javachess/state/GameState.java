package com.javachess.state;

import java.util.ArrayList;
import java.util.List;

import com.javachess.board.Board;
import com.javachess.game.StateContext;
import com.javachess.move.Move;
import com.javachess.state.board.StandardState;

public class GameState {
	
	private BoardState boardState;
	private EnPassantState enPassantState;
	private CastlingState castlingState;	
	
	public GameState() {
		boardState = new StandardState();
		enPassantState = new EnPassantState();
		castlingState = new CastlingState();
	}

	public void notifyMove(Move move, Board board, StateContext context) {
		enPassantState.notifyMove(move, board);
		castlingState.notifyMove(move, board);
		
		boardState = boardState.executeTransition(move, board, context);
	}
	
	public List<Move> getCtxMoves() {
		List<Move> moves = new ArrayList<Move>();
		
		moves.addAll(enPassantState.getCtxMoves());
		moves.addAll(castlingState.getCtxMoves());
		
		return moves;
	}
}
