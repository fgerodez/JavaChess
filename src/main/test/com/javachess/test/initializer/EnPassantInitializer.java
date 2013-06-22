package com.javachess.test.initializer;

import com.javachess.board.Board;
import com.javachess.board.Square;
import com.javachess.board.initializer.BoardInitializer;
import com.javachess.piece.Piece;

public class EnPassantInitializer implements BoardInitializer{

	private boolean left;
	private boolean right;
	
	public EnPassantInitializer(boolean left, boolean right) {
		this.left = left;
		this.right = right;
	}
	
	@Override
	public void init(Board board) {
		board.setPieceAt(Square.at(3, 4), Piece.WHITE_PAWN);
		
		if (right)
			board.setPieceAt(Square.at(3, 3), Piece.BLACK_PAWN);
		
		if (left)
			board.setPieceAt(Square.at(3, 5), Piece.BLACK_PAWN);
	}

}
