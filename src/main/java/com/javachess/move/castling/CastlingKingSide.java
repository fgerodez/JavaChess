package com.javachess.move.castling;

import com.javachess.board.Board;
import com.javachess.board.Square;
import com.javachess.piece.Color;

public class CastlingKingSide extends Castling {

	public CastlingKingSide(Color color, Board board) {
		super(color, board);
	}
	
	@Override
	public Square getDst() {
		return Square.atOffset(kingSquare, 0, 2);
	}

	@Override
	protected Square getRookSrc() {
		return Square.atOffset(kingSquare, 0, 3);
	}

	@Override
	protected Square getRookDst() {
		return Square.atOffset(kingSquare, 0, 1);
	}
}
