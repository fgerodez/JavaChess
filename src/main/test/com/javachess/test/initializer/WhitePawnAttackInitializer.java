package com.javachess.test.initializer;


import com.javachess.board.Board;
import com.javachess.board.Square;
import com.javachess.board.initializer.BoardInitializer;
import com.javachess.piece.Piece;

public class WhitePawnAttackInitializer implements BoardInitializer{

	@Override
	public void init(Board board) {	
		
		board.setPieceAt(Square.at(2, 1), Piece.BLACK_PAWN);
		
		for (int col = 0; col < 8; col++) {
			board.setPieceAt(Square.at(1, col), Piece.WHITE_PAWN);
		}
		
		board.setPieceAt(Square.at(0, 4), Piece.WHITE_KING);
	}

}
