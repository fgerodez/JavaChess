package com.javachess.move;

import com.javachess.board.Board;
import com.javachess.board.Square;
import com.javachess.piece.Piece;

public class PawnPush extends StandardMove {

	public PawnPush(Square srcSquare, Board board) {
		super(srcSquare, null, board);
		
		Piece piece = board.at(srcSquare);
		
		this.dstSquare = Square.atOffset(srcSquare, 2 * piece.color().dir(), 0);
	}
}
