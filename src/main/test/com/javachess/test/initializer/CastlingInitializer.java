package com.javachess.test.initializer;

import com.javachess.board.Board;
import com.javachess.board.Square;
import com.javachess.board.initializer.BoardInitializer;
import com.javachess.piece.Piece;

public class CastlingInitializer implements BoardInitializer{
	
	@Override
	public void init(Board board) {
		board.setPieceAt(Square.at(7, 0), Piece.BLACK_ROOK);
		board.setPieceAt(Square.at(7, 4), Piece.BLACK_KING);
		board.setPieceAt(Square.at(7, 7), Piece.BLACK_ROOK);
		
		board.setPieceAt(Square.at(1, 0), Piece.WHITE_PAWN);
		board.setPieceAt(Square.at(0, 4), Piece.WHITE_KING);
	}

}
