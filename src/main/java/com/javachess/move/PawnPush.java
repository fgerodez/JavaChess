package com.javachess.move;

import com.javachess.board.Board;
import com.javachess.board.Square;

public class PawnPush extends StandardMove {

	public PawnPush(Square srcSquare, Square dstSquare, Board board) {
		super(srcSquare, dstSquare, board);
	}
}
