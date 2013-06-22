package com.javachess.test.initializer;

import com.javachess.board.Board;
import com.javachess.board.Square;
import com.javachess.board.initializer.BoardInitializer;
import com.javachess.piece.Piece;

public class CheckMateInitializer implements BoardInitializer {

	@Override
	public void init(Board board) {
		board.setPieceAt(Square.at(0, 5), Piece.WHITE_KING);
		board.setPieceAt(Square.at(2, 7), Piece.WHITE_ROOK);
		board.setPieceAt(Square.at(0, 7), Piece.BLACK_KING);
	}
}
