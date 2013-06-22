package com.javachess.state;

import java.util.ArrayList;
import java.util.List;

import com.javachess.board.Board;
import com.javachess.move.Move;
import com.javachess.piece.Color;

public class GameState implements BoardState {
	
	private BoardState boardState;
	private EnPassantState enPassantState;
	private CastlingState castlingState;	
	
	public GameState() {
		boardState = new StandardState();
		enPassantState = new EnPassantState();
		castlingState = new CastlingState();
	}

	@Override
	public void executeTransition(Move move, Board board, StateContext context) {
		enPassantState.executeTransition(move, board);
		castlingState.executeTransition(move, board);
		boardState.executeTransition(move, board, context);
	}
	
	public List<Move> getCtxMoves(Color color) {
		List<Move> moves = new ArrayList<Move>();
		
		moves.addAll(enPassantState.getCtxMoves());
		moves.addAll(castlingState.getCtxMoves(color, null));
		
		return moves;
	}
	
	void setBoardState(BoardState boardState) {
		this.boardState = boardState;
	}
}
