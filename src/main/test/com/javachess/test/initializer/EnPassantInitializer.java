package com.javachess.test.initializer;

import com.javachess.board.Board;
import com.javachess.board.Square;
import com.javachess.board.initializer.BoardInitializer;
import com.javachess.piece.Piece;

public class EnPassantInitializer implements BoardInitializer {

	@Override
	public void init(Board board) {
		board.setPieceAt(Square.at(1, 4), Piece.WHITE_PAWN);
		board.setPieceAt(Square.at(3, 3), Piece.BLACK_PAWN);
		board.setPieceAt(Square.at(3, 5), Piece.BLACK_PAWN);
	}

}
