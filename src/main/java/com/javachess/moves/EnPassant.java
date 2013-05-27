package com.javachess.moves;

import com.javachess.board.Board;
import com.javachess.board.Square;

public class EnPassant extends StandardMove {

	public EnPassant(Square srcSquare, Square dstSquare, Board board) {
		super(srcSquare, dstSquare, board);
	}

}
