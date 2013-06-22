package com.javachess.test.initializer;

import com.javachess.board.Board;
import com.javachess.board.Square;
import com.javachess.board.initializer.BoardInitializer;
import com.javachess.piece.Piece;

public class StaleMateInitializer implements BoardInitializer {

	@Override
	public void init(Board board) {
		board.setPieceAt(Square.at(5, 5), Piece.WHITE_KING);
		board.setPieceAt(Square.at(6, 5), Piece.WHITE_PAWN);
		board.setPieceAt(Square.at(7, 5), Piece.BLACK_KING);
	}
}
